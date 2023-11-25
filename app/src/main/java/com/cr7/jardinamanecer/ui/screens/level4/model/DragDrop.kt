package com.cr7.jardinamanecer.ui.screens.level4.model

import android.content.Context
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.*
import androidx.compose.ui.text.style.TextForegroundStyle.Unspecified.alpha
import androidx.compose.ui.unit.IntSize
import kotlinx.coroutines.launch
import kotlin.math.abs

internal val LocalDragTargetInfo = compositionLocalOf { DragTargetInfo() }

internal class DragTargetInfo {
    var isDragging: Boolean? by mutableStateOf(false)
    var dragPosition by mutableStateOf(Offset.Zero)
    var dragOffset by mutableStateOf(Offset.Zero)
    var draggableComposable by mutableStateOf<(@Composable () -> Unit)?>(null)
    var dataToDrop by mutableStateOf<Any?>(null)
    var itemDropped: Boolean by mutableStateOf(false)
    var absolutePositionX: Float by mutableStateOf(0F)
    var absolutePositionY: Float by mutableStateOf(0F)
    var enabled by mutableStateOf(true)
}

@Composable
fun LongPressDraggable(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    val state = remember { DragTargetInfo() }

    CompositionLocalProvider(
        LocalDragTargetInfo provides state
    ) {
        Box(modifier = modifier.fillMaxSize())
        {
            content()
            if (state.isDragging == true) {
                var targetSize by remember {
                    mutableStateOf(IntSize.Zero)
                }
                Box(modifier = Modifier
                    .graphicsLayer {
                        val offset = (state.dragPosition + state.dragOffset)
                        // will scale the dragged item being dragged by 50%
                        scaleX = 0.5f
                        scaleY = 0.5f
                        // adds a bit of transparency
                        alpha = if (targetSize == IntSize.Zero) 0f else .9f
                        // horizontal displacement
                        translationX = offset.x.minus(targetSize.width / 2)
                        // vertical displacement
                        translationY = offset.y.minus(targetSize.height / 2)
                    }
                    .onGloballyPositioned {
                        targetSize = it.size
                        it.let { coordinates ->
                            state.absolutePositionX = coordinates.positionInRoot().x
                            state.absolutePositionY = coordinates.positionInRoot().y
                        }
                    }
                ) {
                    state.draggableComposable?.invoke()
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T> DragTarget(
    context: Context,
    pagerSize: Int,
    verticalPagerState: PagerState? = null, // if you have nested / multi paged app
    horizontalPagerState: PagerState? = null,
    modifier: Modifier,
    dataToDrop: Any? = null, // change type here to your data model class
    enabled: Boolean = true,
    content: @Composable (shouldAnimate: Boolean) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    var currentPosition by remember { mutableStateOf(Offset.Zero) }
    val currentState = LocalDragTargetInfo.current

    currentState.enabled = enabled

    Box(modifier = modifier
        .onGloballyPositioned {
            currentPosition = it.localToWindow(Offset.Zero)
        }
        .pointerInput(Unit) {
            if (enabled) {
                detectDragGesturesAfterLongPress(
                    onDragStart = {

                        currentState.dataToDrop = dataToDrop
                        currentState.isDragging = true
                        currentState.dragPosition = currentPosition + it
                        currentState.draggableComposable = {
                            content(false) // render scaled item without animation }

                        }
                    }, onDrag = { change, dragAmount ->
                        change.consume()

                        currentState.itemDropped =
                            false // used to prevent drop target from multiple re-renders

                        currentState.dragOffset += Offset(dragAmount.x, dragAmount.y)

                        val xOffset = abs(currentState.dragOffset.x)
                        val yOffset = abs(currentState.dragOffset.y)

                        coroutineScope.launch {

                            // this is a flag only for demo purposes, change as per your needs
                            val boundDragEnabled = false

                            if (boundDragEnabled) {
                                // use this for dragging after the user has dragged the item outside a bound around the original item itself
                                if (xOffset > 20 && yOffset > 20) {
                                    verticalPagerState?.animateScrollToPage(
                                        1,
                                        animationSpec = tween(
                                            durationMillis = 300,
                                            easing = androidx.compose.animation.core.EaseOutCirc
                                        )
                                    )
                                }
                            } else {
                                // for dragging to and fro from different pages in the pager
                                val currentPage = horizontalPagerState?.currentPage
                                val dragPositionX =
                                    currentState.dragPosition.x + currentState.dragOffset.x
                                val dragPositionY =
                                    currentState.dragPosition.y + currentState.dragOffset.y

                                val displayMetrics = context.resources.displayMetrics

                                // if item is very close to left edge of page, move to previous page
                                if (dragPositionX < 60) {
                                    currentPage?.let {
                                        if (it > 1) {
                                            horizontalPagerState.animateScrollToPage(currentPage - 1)
                                        }
                                    }
                                } else if (displayMetrics.widthPixels - dragPositionX < 60) {
                                    // if item is very close to right edge of page, move to next page
                                    currentPage?.let {
                                        if (it < pagerSize) {
                                            horizontalPagerState.animateScrollToPage(currentPage + 1)
                                        }
                                    }
                                }
                            }
                        }

                    }, onDragEnd = {
                        currentState.isDragging = false
                        currentState.dragOffset = Offset.Zero
                    }, onDragCancel = {
                        currentState.isDragging = false
                        currentState.dragOffset = Offset.Zero
                    })
            }
        }, contentAlignment = Alignment.Center
    ) {
        content(true) // render positioned content with animation
    }
}

@Composable
fun <T> DropTarget(
    modifier: Modifier,
    content: @Composable() (BoxScope.(isInBound: Boolean, data: T?) -> Unit)
) {
    val dragInfo = LocalDragTargetInfo.current
    val dragPosition = dragInfo.dragPosition
    val dragOffset = dragInfo.dragOffset
    var isCurrentDropTarget by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier
            .onGloballyPositioned {
                it.boundsInWindow().let { rect ->
                    isCurrentDropTarget = rect.contains(dragPosition + dragOffset)
                }
            }
    ) {
        val data =
            if (isCurrentDropTarget) dragInfo.dataToDrop as T? else null
        println("data: $data")
        content(isCurrentDropTarget, data)
    }
}