import React, { useState } from 'react'
import useUsers from './useUsers'
import ListElement from './listofusers/ListElement'
import Searchbar from './listofusers/Searchbar'
import ListHeader from './listofusers/ListHeader'

const UsersList = () => {
  const [searchKeyword, setSearchKeyword] = useState('')
  const [sortKey, setSortKey] = useState('')
  const [isSortAscending, setAscendingSort] = useState(true)
  const { users } = useUsers(searchKeyword, sortKey, isSortAscending)

  const onColumnClick = (value) => {
    setAscendingSort(!isSortAscending)
    setSortKey(value)
  }

  return (
    <div>
      <Searchbar setSearchKeyword={setSearchKeyword} />
      <table className="table table-striped">
        <ListHeader
          onColumnClick={onColumnClick}
          isSortAscending={isSortAscending}
          sortKey={sortKey}
        />
        <tbody>
          {users?.map((user) => (
            <ListElement user={user} key={user.id} />
          ))}
        </tbody>
      </table>
    </div>
  )
}

export default UsersList
