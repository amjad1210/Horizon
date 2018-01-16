import React, { PureComponent } from 'react'
import PropTypes from 'prop-types'

import _ from 'lodash'

import NetworkWindow from '../Windows/NetworkWindow'

class WindowManager extends PureComponent {
  static propTypes = {
    openedWindows: PropTypes.object.isRequired,
    actions: PropTypes.object.isRequired
  }

  render() {
    const { openedWindows } = this.props
    const renderWindows = _.map(openedWindows, (window, i) => {
      const Window = window.component
      return(
        <Window key={window.name} window={window} />
      )
    })

    return (
      <div>
      </div>
    )
  }
}

export default WindowManager
