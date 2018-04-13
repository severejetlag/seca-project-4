import React from 'react'

const PostDeleteButton = props =>{
  return(
    <div>
      <button className='btn btn-danger' onClick={() => props.deletePost(props.id, props.index)}>
        Delete Post
      </button>
    </div>
  )
}

export default PostDeleteButton
