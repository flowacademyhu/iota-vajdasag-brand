import React, { useState } from 'react'
import { Button, Modal } from 'react-bootstrap'
import { useTranslation } from 'react-i18next/'
import ResponseModal from '../modals/ResponseModal'
import { deleteProduct } from '../../communications/userApi'

const DeleteProductButton = ({ productId }) => {
  const [showConfirmDeletion, setShowConfirmDeletion] = useState(false)
  const [showResponseModal, setShowResponseModal] = useState(false)
  const [responseModalTitle, setResponseModalTitle] = useState('')
  const { t } = useTranslation()

  const confirmModalHandler = (session) => {
    if (session) {
      setResponseModalTitle(t('userListElement.successful'))
    } else {
      setResponseModalTitle(t('userListElement.unsuccessful'))
    }
    setShowResponseModal(true)
  }

  const deleteUser = async () => {
    setShowConfirmDeletion(false)
    try {
      await deleteProduct(1)
      confirmModalHandler(true)
    } catch (error) {
      confirmModalHandler(false)
    }
  }

  return (
    <>
      <ResponseModal
        setShowResponseModal={setShowResponseModal}
        showResponseModal={showResponseModal}
        title={responseModalTitle}
      ></ResponseModal>
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

export default DeleteProductButton
