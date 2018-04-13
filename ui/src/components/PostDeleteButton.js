import React from 'react'

const PostDeleteButton = props =>{
  return(
    <div>
      <button className='btn btn-danger' onClick={() => props.deletePost(props.id, props.index)}>
        Delete Post <i class="fas fa-trash-alt"></i>
      </button>
    </div>
  )
}

export default PostDeleteButton
