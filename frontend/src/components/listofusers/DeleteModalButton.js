import React from 'react'
import { Button, Modal } from 'react-bootstrap'
import { useTranslation } from 'react-i18next/'

const DeleteModalButton = ({
  userId,
  handleDelete,
  setShowConfirmDeletion,
  showConfirmDeletion,
}) => {
  const { t } = useTranslation()

  return (
    <>
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
          <Button variant="danger" onClick={() => handleDelete(userId)}>
            {t('userListElement.delete')}
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  )
}

export default DeleteModalButton
