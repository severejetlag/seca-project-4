import React from 'react'

const PostToggleVerifiedButton = props =>{
  return(
    <div>
        {
          props.post.verified ?
          <button className='btn btn-danger' onClick={() => props.toggleVerified(props.id, props.index, props.post)}>
            Unverify
          </button>
          :
          <button className='btn btn-success' onClick={() => props.toggleVerified(props.id, props.index, props.post)}>
            Verify
          </button>
        }
    </div>
  )
}

export default PostToggleVerifiedButton
