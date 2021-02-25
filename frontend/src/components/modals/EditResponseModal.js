import React from 'react'
import { Modal, Button } from 'react-bootstrap'
import { useTranslation } from 'react-i18next/'
import { useHistory } from 'react-router-dom'

const EditResponseModal = ({
  setShowResponseModal,
  showResponseModal,
  title,
}) => {
  const { t } = useTranslation()
  let history = useHistory()

  const handleClick = () => {
    setShowResponseModal(false)
    if (!title.includes('unsuccessful')) {
      history.push('/super-admin/products')
    }
  }

  return (
    <Modal
      show={showResponseModal}
      onHide={() => setShowResponseModal(false)}
      animation={false}
    >
      <Modal.Body>{title} </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={handleClick}>
          {t('userListElement.close')}
        </Button>
      </Modal.Footer>
    </Modal>
  )
}

export default EditResponseModal
