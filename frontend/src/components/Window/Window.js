import React, { PureComponent } from 'react'
import PropTypes from 'prop-types'

import Rnd from 'react-rnd'

const resizeHandleStyles = {
  top: { cursor: 'n-resize' },
  right: { cursor: 'e-resize' },
  bottom: { cursor: 's-resize' },
  left: { cursor: 'w-resize' }
}

class Window extends PureComponent {
  static propTypes = {
    title: PropTypes.string.isRequired,
    width: PropTypes.number.isRequired,
    height: PropTypes.number.isRequired
  }

  constructor(props) {
    super(props)

    const { clientWidth, clientHeight } = document.body;
    const { width, height } = props;

    const x =  (clientWidth / 2) - (width / 2);
    const y = (clientHeight / 2) - (height / 2);

    this.state = {
      width: width,
      height: height,
      x: x,
      y: y
    }
  }

  render() {
    const { width, height, x, y } = this.state
    const { title } = this.props

    return (
      <Rnd className='window'
        resizeHandleStyles={resizeHandleStyles}
        cancel='.body'
        bounds='parent'
        size={{ width: width,  height: height }}
        position={{ x: x, y: y }}
        onDragStop={(e, d) => { this.setState({ x: d.x, y: d.y }) }}
        onResize={(e, direction, ref, delta, position) => {
          this.setState({
            width: ref.offsetWidth,
            height: ref.offsetHeight,
            ...position
          });
        }}
      >
        <div className='title'>
          {title}
          <div className='buttons'>
            <div className='button minimize'/>
            <div className='button maximize'/>
            <div className='button close'/>
          </div>
        </div>
        <div className='body'>
          {this.props.children}
        </div>
      </Rnd>
    )
  }
}

export default Window
