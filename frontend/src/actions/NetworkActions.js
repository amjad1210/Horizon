import { CALL_API } from 'redux-api-middleware'

import { FETCH_PORTS_REQUEST, FETCH_PORTS_SUCCESS, FETCH_PORTS_FAILURE } from '../constants/NetworkActionTypes'

export const fetchPorts = () => ({
  [CALL_API]: {
    endpoint: '/api/v1/port/all',
    method: 'GET',
    types: [FETCH_PORTS_REQUEST, FETCH_PORTS_SUCCESS, FETCH_PORTS_FAILURE]
  }
})
