import React from 'react'

const PostToggleVerifiedButton = props =>{
  return(
    <div>
        {
          props.post.verified ?
          <button className='btn btn-danger' onClick={() => props.toggleVerified(props.id, props.index, props.post)}>
            Unverify <i class="fas fa-check"></i>
          </button>
          :
          <button className='btn btn-success' onClick={() => props.toggleVerified(props.id, props.index, props.post)}>
            Verify <i class="fas fa-check"></i>
          </button>
        }
    </div>
  )
}

export default PostToggleVerifiedButton
