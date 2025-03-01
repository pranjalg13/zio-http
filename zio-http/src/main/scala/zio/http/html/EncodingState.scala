/*
 * Copyright 2021 - 2023 Sporta Technologies PVT LTD & the ZIO HTTP contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package zio.http.html

private[html] sealed trait EncodingState {
  def nextElemSeparator: String
  def inner: EncodingState
}

object EncodingState {
  case object NoIndentation extends EncodingState {
    val nextElemSeparator: String = ""
    def inner: EncodingState      = NoIndentation
  }

  final case class Indentation(current: Int, spaces: Int) extends EncodingState {
    lazy val nextElemSeparator: String = "\n" + (" " * (current * spaces))
    def inner: EncodingState           = Indentation(current + 1, spaces)
  }
}
