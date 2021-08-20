# PCM
PCM是Pulse Code Modulation，脉冲编码调制。
PCM音频数据是未经压缩的音频采样数据裸流，它是由模拟信号经过采样、量化、编码转换成的标准数字音频数据。


描述PCM数据的6个参数：

Sample Rate : 采样频率。8kHz(电话)、44.1kHz(CD)、48kHz(DVD)。
Sample Size : 量化位数。通常该值为16-bit。
Number of Channels : 通道个数。常见的音频有立体声(stereo)和单声道(mono)两种类型，立体声包含左声道和右声道。另外还有环绕立体声等其它不太常用的类型。
Sign : 表示样本数据是否是有符号位，比如用一字节表示的样本数据，有符号的话表示范围为-128 ~ 127，无符号是0 ~ 255。
Byte Ordering : 字节序。字节序是little-endian还是big-endian。通常均为little-endian。字节序说明见第4节。
Integer Or Floating Point : 整形或浮点型。大多数格式的PCM样本数据使用整形表示，而在一些对精度要求高的应用方面，使用浮点类型表示PCM样本数据。