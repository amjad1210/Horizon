import { FETCH_PORTS_REQUEST, FETCH_PORTS_SUCCESS, FETCH_PORTS_FAILURE } from '../constants/NetworkActionTypes'

const initialState = {
  isLoading: false,
  payload: [],
  error: false
}

export default function network(state = initialState, action) {
  switch(action.type) {
    case FETCH_PORTS_REQUEST:
      return {
        ...state,
        isLoading: true,
        error: false
      }
    case FETCH_PORTS_SUCCESS:
      return {
        ...state,
        isLoading: false,
        payload: action.payload
      }
    case FETCH_PORTS_FAILURE:
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
