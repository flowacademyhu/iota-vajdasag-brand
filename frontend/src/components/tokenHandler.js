import { useContext} from "react";
import  {TokenContext}  from "../Authenticator";




export const useTokenStateHandler = () => {
    const [ token, setToken ] = useContext(TokenContext);


    const getTokenFromStorage = () => {
        return sessionStorage.getItem("token");
    }

    const writeToken = (tokenInput) => {
        console.log("writeToken incoming: ", tokenInput)
        sessionStorage.setItem("token", tokenInput)
        setToken(tokenInput)
        console.log("A token az contextben-ban:" + token)
    }

    const deleteToken = () => {
        sessionStorage.removeItem("token")
    }

    return {
        getTokenFromStorage,
        writeToken,
        deleteToken
    }
}

