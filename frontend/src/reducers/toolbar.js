import { TOGGLE_APP_MENU } from '../constants/ToolBarActionTypes'

const initialState = {
  isAppMenuActive: false
}

export default function toolbar(state = initialState, action) {
  switch(action.type) {
    case TOGGLE_APP_MENU:
      return {
        ...state,
        isAppMenuActive: !state.isAppMenuActive
      }
    default:
      return state
  }
}
