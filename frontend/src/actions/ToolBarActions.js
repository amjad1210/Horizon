import { TOGGLE_APP_MENU } from '../constants/ToolBarActionTypes'

export const toggleAppMenu = () => dispatch => {
  return dispatch({
    type: TOGGLE_APP_MENU
  })
}
