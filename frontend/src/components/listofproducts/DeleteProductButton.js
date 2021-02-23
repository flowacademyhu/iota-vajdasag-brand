import React, { useState } from 'react'
import { Button, Modal } from 'react-bootstrap'
import { useTranslation } from 'react-i18next/'
import DeletionResponseModal from '../listofusers/DeletionResponseModal'
import { deleteProduct } from '../../communications/userApi'
//import useProducts from '../useProducts'

const DeleteModalButton = ({ productId }) => {
    const [showConfirmDeletion, setShowConfirmDeletion] = useState(false)
    const [showResponseModal, setShowResponseModal] = useState(false)
    const [responseModalTitle, setResponseModalTitle] = useState("")
    //const { fetchProducts } = useProducts() fejlesztés alatt áll
    const { t } = useTranslation()

    const confirmModalHandler = (session) => {
        let output;
        if (session) {
            setResponseModalTitle(t('userListElement.successful'))
        } else {
            setResponseModalTitle(t('userListElement.unsuccessful'))
        }
        setShowResponseModal(true)
        return output
    }

    const deleteUser = async () => {
        setShowConfirmDeletion(false)
        try {
            await deleteProduct(1)
            //fetchProducts()  fejlesztés alatt
            confirmModalHandler(true)
        } catch (error) {
            confirmModalHandler(false)
        }
    }

    return (
        <>
            <DeletionResponseModal
                setShowResponseModal={setShowResponseModal}
                showResponseModal={showResponseModal}
                title={responseModalTitle}
            ></DeletionResponseModal>
            <Button variant="danger" onClick={() => setShowConfirmDeletion(true)}>
                {t('productList.delete')}
            </Button>
            <Modal
                show={showConfirmDeletion}
                onHide={() => setShowConfirmDeletion(false)}
                animation={false}
            >
                <Modal.Header>
                    <Modal.Title> {t('productList.confirmDeleteTitle')}</Modal.Title>
                </Modal.Header>
                <Modal.Body> {t('productList.confirmDelete')}</Modal.Body>
                <Modal.Footer>
                    <Button
                        variant="secondary"
                        onClick={() => setShowConfirmDeletion(false)}
                    >
                        {t('productList.close')}
                    </Button>
                    <Button variant="danger" onClick={deleteUser}>
                        {t('productList.delete')}
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    )
}

export default DeleteModalButton