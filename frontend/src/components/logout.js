import useTokenStateHandler from "./tokenHandler";
import { BrowserRouter as Router, Route, NavLink, Redirect } from 'react-router-dom'



const Logout = () => {
    const { deleteToken } = useTokenStateHandler()
    deleteToken();

    return (
        <Redirect to="/login" />
    );


}

export default Logout