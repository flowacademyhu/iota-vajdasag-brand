import React from 'react'
import { TokenProvider } from './TokenContext'
import Main from './components/Main'

export default function App() {
  return (
    <>
      <TokenProvider>
        <Main></Main>
      </TokenProvider>
    </>
  )
}
