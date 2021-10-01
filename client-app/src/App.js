import React from 'react';

import { GlobalStyle } from './Components/GlobalStyle';
import Header from "./Components/Header";
import Container from "./Components/Container";

function App () {
  return (
    <>
      <GlobalStyle />
      <Header />
      <Container />
    </>
  );
}

export default App;
