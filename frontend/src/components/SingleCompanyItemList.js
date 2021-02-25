import React from 'react'
import { GetItemsById } from './useItems'
import ListElement from './listOfItems/ListElement'
import ListHeader from './listOfItems/ListHeader'

const SingleCompanyItemList = () => {
  const { companysItems } = GetItemsById()
  return (
    <div>
      <h2
        style={{
          padding: '20px',
        }}
      >
        {companysItems[0]?.name}
      </h2>
      <div className="table-responsive">
        <div className="col-9">
          <table className="table table-striped table-sm">
            <ListHeader />
            <tbody>
              {companysItems?.map((item) => (
                <ListElement item={item} key={item.id} />
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  )
}

export default SingleCompanyItemList
