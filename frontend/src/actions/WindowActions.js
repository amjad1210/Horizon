import { OPEN_WINDOW, DRAG_WINDOW, RESIZE_WINDOW, MINIMIZE_WINDOW, MAXIMIZE_WINDOW, CLOSE_WINDOW } from '../constants/WindowActionTypes'

export const openWindow = name => dispatch => {
  return dispatch({
    type: OPEN_WINDOW,
    name
  })
}

export const dragWindow = name => dispatch => {
  return dispatch({
    type: DRAG_WINDOW,
    name
  })
}

export const resizeWindow = name => dispatch => {
  return dispatch({
    type: RESIZE_WINDOW,
    name
  })
}

export const minimizeWindow = name => dispatch => {
  return dispatch({
    type: MINIMIZE_WINDOW,
    name
  })
}

export const maximizeWindow = name => dispatch => {
  return dispatch({
    type: MAXIMIZE_WINDOW,
    name
  })
}

export const closeWindow = name => dispatch => {
  return dispatch({
    type: CLOSE_WINDOW,
    name
  })
}
