import React, { useState } from 'react'
import { Button, Modal } from 'react-bootstrap'
import { useTranslation } from 'react-i18next/'
import DeletionFailedModal from './DeletionFailedModal'
import { deleteUserRegistration } from '../../communications/userApi'
import useUsers from '../useUsers'

const DeleteModalButton = ({ userId }) => {
  const [showConfirmDeletion, setShowConfirmDeletion] = useState(false)
  const [showFailureModal, setShowFailureModal] = useState(false)
  const { fetchUsers } = useUsers()
  const { t } = useTranslation()

  const deleteUser = () => {
    setShowConfirmDeletion(false)
    const responseStatus = deleteUserRegistration(userId)

    if (responseStatus === 200) {
      fetchUsers()
    } else {
      setShowFailureModal(true)
    }
  }

  return (
    <>
      <DeletionFailedModal
        setShowFailureModal={setShowFailureModal}
        showFailureModal={showFailureModal}
      ></DeletionFailedModal>
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
          <Button variant="danger" onClick={deleteUser}>
          {t('userListElement.delete')}
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  )
}

export default DeleteModalButton
