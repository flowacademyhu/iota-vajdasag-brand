import React, { useState } from 'react'
import { Button, Modal } from 'react-bootstrap'
import { useTranslation } from 'react-i18next/'
import ResponseModal from '../modals/ResponseModal'

const DeleteModalButton = ({ userId, deleteUser }) => {
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

  const handleDelete = () => {
    setShowConfirmDeletion(false)
    try {
      deleteUser(userId)
      confirmModalHandler(true)
    } catch (error) {
      setShowResponseModal(true)
      confirmModalHandler(false)
    }
  }

  const onClose = () => {
    setShowResponseModal(false)
  }

  return (
    <>
      <ResponseModal
        onClose={onClose}
        showResponseModal={showResponseModal}
        title={responseModalTitle}
      />
      <Button variant="danger" onClick={() => setShowConfirmDeletion(true)}>
        {t('userListElement.delete')}
      </Button>
      <Modal
        show={showConfirmDeletion}
        onHide={() => setShowConfirmDeletion(false)}
        animation={false}
      >
        <Modal.Header>
          <Modal.Title> {t('userListElement.confirmDeleteTitle')}</Modal.Title>
        </Modal.Header>
        <Modal.Body> {t('userListElement.confirmDelete')}</Modal.Body>
        <Modal.Footer>
          <Button
            variant="secondary"
            onClick={() => setShowConfirmDeletion(false)}
          >
            {t('userListElement.close')}
          </Button>
          <Button variant="danger" onClick={handleDelete}>
            {t('userListElement.delete')}
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  )
}

export default DeleteModalButton
