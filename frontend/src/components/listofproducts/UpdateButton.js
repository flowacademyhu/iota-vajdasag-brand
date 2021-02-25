import React from 'react'
import { Button } from 'react-bootstrap'
import { Link } from 'react-router-dom'


const UpdateButton = ({ text, productid }) => {
    const targetUrl = "/updateProduct/" + productid
    return (

        <Link to={targetUrl}>
            <Button>{text}</Button >
        </Link >

    )
}

export default UpdateButton