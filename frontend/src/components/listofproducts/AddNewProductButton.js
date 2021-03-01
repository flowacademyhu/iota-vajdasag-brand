import React from 'react';
import { Link } from 'react-router-dom'
import { Button } from "react-bootstrap";
import { useTranslation } from 'react-i18next'



const AddNewProductButton = () => {
    const { t } = useTranslation()
    return (
        <Link to="items/newItem">
            <Button>
                {t("editProduct.addNewProduct")}
            </Button>
        </Link>
    );
}

export default AddNewProductButton;