import { useContext } from "react";
import { TokenContext } from "../TokenContext";




export const useTokenStateHandler = () => {
    const {setToken, token} = useContext(TokenContext);

    const writeToken = (tokenInput) => {
        console.log("writeToken incoming: ", tokenInput)
        sessionStorage.setItem("token", tokenInput)
        setToken(tokenInput)
        console.log("A token az contextben-ban:" + token)
    }

    const deleteToken = () => {
        sessionStorage.removeItem("token")
        setToken(undefined)
    }

    return {
        writeToken,
        deleteToken
    }
}

