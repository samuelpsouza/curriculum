import React, { Component } from 'react';
import AppRouter from './AppRouter';
import './App.css';

import { Provider } from 'react-redux'
import { PersistGate } from 'redux-persist/integration/react'

import { store, persistor } from './redux/store'
import { history } from './redux/store'

class App extends Component {
  render() {
    return (
      <Provider store={store} >
        <PersistGate loading={null} persistor={persistor}>
          <AppRouter history={history} />
        </PersistGate>
      </Provider>
    );
  }
}

export default App;
