import React, { PureComponent } from 'react'

import * as screenfull from 'screenfull'

import { Menu } from 'semantic-ui-react'

class ToggleFullscreen extends PureComponent {
  state = { isFullscreen: false }

  handleToggle = () => {
    if (screenfull.enabled) {
      screenfull.toggle()
      this.setState({ isFullscreen: !this.state.isFullscreen })
    }
  }

  render() {
    const { isFullscreen } = this.state

    return (
      <Menu.Item icon={isFullscreen ? 'compress' : 'expand'} onClick={this.handleToggle}/>
    )
  }
}

export default ToggleFullscreen
