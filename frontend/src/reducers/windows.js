import { OPEN_WINDOW, DRAG_WINDOW, RESIZE_WINDOW, MINIMIZE_WINDOW, MAXIMIZE_WINDOW, CLOSE_WINDOW } from '../constants/WindowActionTypes'

const initialState = {
  openedWindows: {}
}

export default function windows(state = initialState, action) {
  switch(action.type) {
    case OPEN_WINDOW:
      return {
        ...state,
        [action.name]: action.name
      }
    case CLOSE_WINDOW:
    return {
      ...state,
      windows: state.openedWindows.filter(window => action.name !== window)
    }
    default:
      return state
  }
}
