import React from 'react'
import { GetItems } from './useItems'
import ListElement from './listOfItems/ListElement'
import ListHeader from './listOfItems/ListHeader'

const FullItemList = () => {
  const { listOfAllItems } = GetItems()
  return (
    <div>
      <div className="table-responsive">
        <div className="col-9">
          <table className="table table-striped table-sm">
            <ListHeader />
            <tbody>
              {listOfAllItems?.map((item) => (
                <ListElement item={item} key={item.id} />
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  )
}

export default FullItemList
