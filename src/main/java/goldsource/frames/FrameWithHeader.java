package goldsource.frames;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.Value;

@Value
@ToString
@AllArgsConstructor
public class FrameWithHeader {

    protected FrameHeader frameHeader;
    protected Frame frame;

}
