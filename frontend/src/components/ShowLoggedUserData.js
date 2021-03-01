import React from 'react'
import { useTranslation } from 'react-i18next'
import useLoggedInUser from '../useLoggedInUser'

const ShowLoggedUserData = () => {
  const { role, email } = useLoggedInUser()
  const { t } = useTranslation()

  return (
    <div className="my-auto text-white">
      {t('headerUserData.loggedIn')}
      {role === 'SuperAdmin' ? 'Superadmin' : email}
    </div>
  )
}

export default ShowLoggedUserData
