package com.github.kavos113.knet.core

import okio.Path

expect fun getLastOpened(): Path?
expect fun setLastOpened(path: Path)