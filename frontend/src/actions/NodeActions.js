import { CALL_API } from 'redux-api-middleware'

import { FETCH_NODES_REQUEST, FETCH_NODES_SUCCESS, FETCH_NODES_FAILURE } from '../constants/NodeActionTypes'

export const fetchNodes = () => ({
  [CALL_API]: {
    endpoint: '/api/v1/node/all',
    method: 'GET',
    types: [FETCH_NODES_REQUEST, FETCH_NODES_SUCCESS, FETCH_NODES_FAILURE]
  }
})
