import React from 'react';
import { Link } from "react-router-dom";

const MenuComponent = ({ title, path }) => {
    return (
        <Link
            className="nav-link"
            to={path}
        >
            <h1>{title}</h1>
        </Link>
    );
}

export default MenuComponent;
