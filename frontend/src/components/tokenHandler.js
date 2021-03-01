import { useContext } from 'react'
import { TokenContext } from '../TokenContext'

const useTokenStateHandler = () => {
  const { setToken } = useContext(TokenContext)

  const writeToken = (tokenInput) => {
    localStorage.setItem('token', tokenInput)
    setToken(tokenInput)
  }

  const deleteToken = () => {
    localStorage.removeItem('token')
    setToken(undefined)
  }

  return {
    writeToken,
    deleteToken,
  }
}

export default useTokenStateHandler
