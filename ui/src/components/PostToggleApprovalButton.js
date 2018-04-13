import React from 'react'

const PostToggleApprovalButton = props =>{
  return(
    <div>
        {
          props.post.approved ?
          <button className='btn btn-danger' onClick={() => props.toggleApproval(props.id, props.index, props.post)}>
            Unapprove
          </button>
          :
          <button className='btn btn-success' onClick={() => props.toggleApproval(props.id, props.index, props.post)}>
            Approve
          </button>
        }
    </div>
  )
}

export default PostToggleApprovalButton
