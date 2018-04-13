import React from 'react'

const PostToggleApprovalButton = props =>{
  return(
    <div>
        {
          props.approved ?
          <button className='btn btn-danger'>
            Unapprove
          </button>
          :
          <button className='btn btn-success'>
            Approve
          </button>
        }
    </div>
  )
}

export default PostToggleApprovalButton
