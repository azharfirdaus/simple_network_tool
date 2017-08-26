/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pip.app.gateaway;

/**
 *
 * @author User
 */
public class InternetProtocol {
    protected int[] segments = new int[4];

    public InternetProtocol() {
        segments[0] = 0;
        segments[1] = 0;
        segments[2] = 0;
        segments[3] = 0;
    }
    
    public InternetProtocol(String verboseAddress) throws InvalidIpAdderssV4FormatException{
        String parts[] = turnIntoFourSegments(verboseAddress);
        for(int i = 0; i < parts.length; i++){
            segments[i] = Integer.valueOf(parts[i]);
            throwExceptionIfSegmentsOutOfRange(segments[i]);
        }
    }

    private void throwExceptionIfSegmentsOutOfRange(int segment) throws InvalidIpAdderssV4FormatException {
        if(segment < 0 || segment > 255)
            throw new InvalidIpAdderssV4FormatException();
    }
    
    private String[] turnIntoFourSegments(String vorboseAddress) throws InvalidIpAdderssV4FormatException{
        String parts[] = vorboseAddress.trim().split("\\.");
        if(parts.length != 4)
            throw new InvalidIpAdderssV4FormatException();
        return parts;
    }
    
    public void setVerboseAddress(String verboseAddress) throws InvalidIpAdderssV4FormatException{
        String parts[] = turnIntoFourSegments(verboseAddress);
        for(int i = 0; i < parts.length; i++){
            segments[i] = Integer.valueOf(parts[i]);
            throwExceptionIfSegmentsOutOfRange(segments[i]);
        }
    }
    
    @Override
    public String toString() {
        return String.format("%d.%d.%d.%d", segments[0], segments[1], segments[2], segments[3]);
    }
    
    public static class InvalidIpAdderssV4FormatException extends Exception{
        
    }
}

