import React, { useState } from 'react'
import { Button, Modal } from 'react-bootstrap'
import { useTranslation } from 'react-i18next/'
import DeletionResponsedModal from '../modals/DeletionResponseModal'
import { deleteUserRegistration } from '../../communications/userApi'
import useUsers from '../useUsers'

const DeleteModalButton = ({ userId }) => {
  const [showConfirmDeletion, setShowConfirmDeletion] = useState(false)
  const [showResponseModal, setShowResponseModal] = useState(false)
  const [responseModalTitle, setResponseModalTitle] = useState('')
  const { fetchUsers } = useUsers()
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
      await deleteUserRegistration(userId)
      confirmModalHandler(true)
      fetchUsers()
    } catch (error) {
      setShowResponseModal(true)
      confirmModalHandler(false)
    }
  }

  return (
    <>
      <DeletionResponsedModal
        setShowResponseModal={setShowResponseModal}
        showResponseModal={showResponseModal}
        title={responseModalTitle}
      ></DeletionResponsedModal>
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
