import { combineReducers } from 'redux'

import toolbar from './toolbar'
import windows from './windows'
import network from './network'
import nodes from './nodes'

const rootReducer = combineReducers({
  toolbar,
  windows,
  network,
  nodes
})

export default rootReducer
