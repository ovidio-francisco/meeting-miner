package userInterfaces;

import java.awt.BorderLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import meetingMiner.Segment;
import utils.Files;

public class PnSegment extends JPanel {

	private static final long serialVersionUID = 1L;
	private Segment segment;
        private static boolean showDescriptions = false;
        private boolean ShowSegmentDoc = false;
        
        
	public PnSegment(Segment seg) {
		this.segment = seg;
		JTextArea taSeg = new JTextArea();
		JScrollPane spSeg = new JScrollPane(taSeg);
		
		JPanel pnBotton = new JPanel(new BorderLayout());
		JPanel pnFiles = new JPanel(new BorderLayout());

                
                taSeg.setText(seg.getText());
		taSeg.setLineWrap(true);
		taSeg.setWrapStyleWord(true);
		taSeg.setMargin(new Insets(4, 4, 4, 4));
		
		this.setBorder(new EmptyBorder(10, 10, 20, 10));
		
		this.setLayout(new BorderLayout());
		this.add(spSeg, BorderLayout.CENTER);
		
                
                if(showDescriptions) {
                    
                
		StringBuilder sb = new StringBuilder();
		for(String s : seg.getDescriptors()){
			if(seg.getMatches().containsKey(s)) {               //se um descritor está contido no matches
				if(seg.descriptorFrequency(s)>1) {
					s = s+"["+seg.descriptorFrequency(s)+"]";
				}
				sb.append("<font color=blue>"+s+"</font>, ");
			}
			else {
				sb.append(s+", ");
			}
		}
		String matches = String.format("[%d/%d] ", seg.matcheCount(), seg.descriptorsCount());
		String descriptors = sb.toString().substring(0, sb.length() - 2);
		descriptors = "<html>"+matches+descriptors+"</html>";
		
		JLabel   lbDescriptors = new JLabel(descriptors);
		pnBotton.add(lbDescriptors, BorderLayout.NORTH);

//		lbDescriptors.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, lbDescriptors.getFont().getSize()));
                }

                
                if(ShowSegmentDoc) {
                    
                    DocLabel lbSegmentDoc  = new DocLabel(Files.getSegmentedDocs(), this.segment.getSegmentDoc());
                    pnFiles.add(lbSegmentDoc, BorderLayout.WEST);
                }

                
                DocLabel lbOriginalDoc = new DocLabel(Files.getOriginalDocs() , this.segment.getOriginalDocument());
		lbOriginalDoc.setBorder(new EmptyBorder(0, 0, 0, 2));
		
		pnFiles.add(lbOriginalDoc, BorderLayout.EAST);
		
		
		pnBotton.add(pnFiles, BorderLayout.SOUTH);
		
		this.add(pnBotton, BorderLayout.SOUTH);
	}

	public Segment getSegment() {
		return segment;
	}
        
        public static void setShowDescriptions(boolean sd) {
            showDescriptions = sd;
        }
}
