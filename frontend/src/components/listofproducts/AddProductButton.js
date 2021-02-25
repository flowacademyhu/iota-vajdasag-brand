import React from 'react';
import { Link } from 'react-router-dom'
import { Button } from 'react-bootstrap'

const AddNewProductButton = () => {
    return ( 
        <Link to="/newProduct">
            <Button>title</Button>
        </Link>
     );
}
export default AddNewProductButton