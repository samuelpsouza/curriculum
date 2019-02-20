import { createStore, applyMiddleware, compose } from 'redux'
import createHistory from 'history/createBrowserHistory'
import thunk from 'redux-thunk'
import { reducer as reduxFormReducer } from 'redux-form'
import { persistStore, persistCombineReducers } from 'redux-persist'
import storage from 'redux-persist/lib/storage'

import reducers from './reducers'

const history = createHistory()
const middlewares = [thunk]

const rootReducer = {
  ...reducers,
  form: reduxFormReducer
}

const persistConfig = {
  key: 'root',
  storage,
  whitelist: ['user']
}

let reduxDevToolsCompose = process.env.REACT_APP_ENV === 'dev' ? window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__() : null

const composeEnhancers = reduxDevToolsCompose || compose
const persistedReducer = persistCombineReducers(persistConfig, rootReducer)
const store = createStore(persistedReducer, composeEnhancers(
  applyMiddleware(...middlewares)
))
const persistor = persistStore(store)

export { store, history, persistor }
