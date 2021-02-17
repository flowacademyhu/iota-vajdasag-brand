import { useContext } from "react";
import { TokenContext } from "../TokenContext";




const useTokenStateHandler = () => {
    const {setToken, token} = useContext(TokenContext);

    const writeToken = (tokenInput) => {
        console.log("writeToken incoming: ", tokenInput)
        localStorage.setItem("token", tokenInput)
        setToken(tokenInput)
        console.log("A token az contextben-ban:" + token)
    }

    const deleteToken = () => {
        localStorage.removeItem("token")
        setToken(undefined)
    }

    return {
        writeToken,
        deleteToken
    }
}

export default useTokenStateHandler

