import React from 'react'
import { render } from 'react-dom'
import { createStore, applyMiddleware } from 'redux'
import { Provider } from 'react-redux'

import thunk from 'redux-thunk'
import { apiMiddleware } from 'redux-api-middleware';
import { createLogger } from 'redux-logger'

import reducer from './reducers'

import App from './containers/App'

import registerServiceWorker from './registerServiceWorker'

import 'semantic-ui-css/semantic.min.css'
import './assets/style.css'

const middleware = [ thunk, apiMiddleware ]
if(process.env.NODE_ENV !== 'production') {
  middleware.push(createLogger())
}

const store = createStore(
  reducer,
  applyMiddleware(...middleware)
)

render(
  <Provider store={store}>
      <App />
  </Provider>,
  document.getElementById('root')
)

registerServiceWorker()
