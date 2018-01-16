import { FETCH_NODES_REQUEST, FETCH_NODES_SUCCESS, FETCH_NODES_FAILURE, INIT_NODE, UPDATE_NODE, DISCONNECT_NODE } from '../constants/NodeActionTypes'

const initialState = {
  isLoading: false,
  payload: [],
  error: false
}

export default function nodes(state = initialState, action) {
  switch(action.type) {
    case FETCH_NODES_REQUEST:
      return {
        ...state,
        isLoading: true,
        error: false
      }
    case FETCH_NODES_SUCCESS:
      return {
        ...state,
        isLoading: false,
        payload: action.payload
      }
    case FETCH_NODES_FAILURE:
      return {
        ...state,
        isLoading: false,
        payload: action.payload.message,
        error: true
      }
    default:
      return state
  }
}
