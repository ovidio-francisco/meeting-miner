package meetingexplorer;

import java.awt.Container;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import meetingMiner.MMTopic;
import meetingMiner.MeetingMiner;
import meetingMiner.Segment;
import preprocessamento.Preprocess;
import segmenters.Segmenter;
import segmenters.algorithms.TextTilingBR;
import topicExtraction.TETConfigurations.TopicExtractionConfiguration;
import userInterfaces.FrConfigExtractor;
import userInterfaces.FrConfigSegmenter;
import userInterfaces.PnSegment;
import utils.Files;
import utils.ShowStatus;
import utils.UIUtils;

/**
 * @author ovidiojf
 */
public class MainForm extends javax.swing.JFrame {

    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();
        
        MeetingMiner.prepareFolders();
	defaultConfiguration();
        
        jtTopics.setModel(null);
        spSegments.getVerticalScrollBar().setUnitIncrement(12);

        PnSegment.setShowDescriptions(false);
                        
        verifyStatus();
        if (isMatricesFound) showTopicTree();
        
        jtTopics.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                
                new Thread() {
		    @Override
		    public void run(){
		
                        setWainting(true);
                        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)jtTopics.getLastSelectedPathComponent(); 
                        showSegmentsByTreeNode(selectedNode);
			setWainting(false);

                    }
		}.start();

                
            }
        });
    }
    
    	private boolean isBasesFolderFound = false;
	private boolean isOrignalDocsFound = false;
	private boolean isArffFileFound = false;
	private boolean isMatricesFound = false;
	private int     originalDocsCount = 0;
	
	ArrayList<PnSegment> pnSegs = new ArrayList<>();

        

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnQuery = new javax.swing.JPanel();
        pnCtrlQuery = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfQuery = new javax.swing.JTextField();
        btExplorar = new javax.swing.JButton();
        pnSearchMode = new javax.swing.JPanel();
        rbRankPorConteudo = new javax.swing.JRadioButton();
        rbFiltrarTopicos = new javax.swing.JRadioButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(100, 0), new java.awt.Dimension(10, 32767));
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(100, 100), new java.awt.Dimension(100, 0), new java.awt.Dimension(10, 32767));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        pnCenter = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        pnTree = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtTopics = new javax.swing.JTree();
        pnResults = new javax.swing.JPanel();
        lbSegmentsCount = new javax.swing.JLabel();
        spSegments = new javax.swing.JScrollPane();
        pnSegments = new javax.swing.JPanel();
        pnStatusBar = new javax.swing.JPanel();
        lbDocsCount = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lbTopicsExtracted = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lbAlgorithm = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu = new javax.swing.JMenu();
        miAddToTheBase = new javax.swing.JMenuItem();
        imExtractTopics = new javax.swing.JMenuItem();
        imShowSegments = new javax.swing.JMenuItem();
        imShowTree = new javax.swing.JMenuItem();
        imConfig = new javax.swing.JMenu();
        imConfigSegmentadores = new javax.swing.JMenuItem();
        imConfgExtratores = new javax.swing.JMenuItem();
        imNumDescriptors = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Meeting Explorer");

        pnQuery.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnQuery.setPreferredSize(new java.awt.Dimension(757, 77));
        pnQuery.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("Digite um assunto:");

        tfQuery.setMargin(new java.awt.Insets(0, 4, 0, 0));
        tfQuery.setMinimumSize(new java.awt.Dimension(4, 27));

        btExplorar.setText("Explorar");
        btExplorar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExplorarActionPerformed(evt);
            }
        });

        pnSearchMode.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        rbRankPorConteudo.setText("Palavras-chave");

        rbFiltrarTopicos.setText("Filtrar por Tópicos");

        javax.swing.GroupLayout pnSearchModeLayout = new javax.swing.GroupLayout(pnSearchMode);
        pnSearchMode.setLayout(pnSearchModeLayout);
        pnSearchModeLayout.setHorizontalGroup(
            pnSearchModeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSearchModeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnSearchModeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbRankPorConteudo)
                    .addComponent(rbFiltrarTopicos))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnSearchModeLayout.setVerticalGroup(
            pnSearchModeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSearchModeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbRankPorConteudo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbFiltrarTopicos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnCtrlQueryLayout = new javax.swing.GroupLayout(pnCtrlQuery);
        pnCtrlQuery.setLayout(pnCtrlQueryLayout);
        pnCtrlQueryLayout.setHorizontalGroup(
            pnCtrlQueryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCtrlQueryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfQuery, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btExplorar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnSearchMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnCtrlQueryLayout.setVerticalGroup(
            pnCtrlQueryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCtrlQueryLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pnCtrlQueryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btExplorar)
                    .addComponent(tfQuery, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(pnSearchMode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnQuery.add(pnCtrlQuery, java.awt.BorderLayout.CENTER);
        pnQuery.add(filler2, java.awt.BorderLayout.LINE_END);
        pnQuery.add(filler1, java.awt.BorderLayout.LINE_START);
        pnQuery.add(filler3, java.awt.BorderLayout.PAGE_START);
        pnQuery.add(filler4, java.awt.BorderLayout.PAGE_END);

        jSplitPane1.setDividerLocation(450);
        jSplitPane1.setDividerSize(6);

        jScrollPane1.setBorder(null);

        jtTopics.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtTopicsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtTopics);

        javax.swing.GroupLayout pnTreeLayout = new javax.swing.GroupLayout(pnTree);
        pnTree.setLayout(pnTreeLayout);
        pnTreeLayout.setHorizontalGroup(
            pnTreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
        );
        pnTreeLayout.setVerticalGroup(
            pnTreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
        );

        jSplitPane1.setLeftComponent(pnTree);

        lbSegmentsCount.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lbSegmentsCount.setText("lbSegmentsCount");

        spSegments.setBorder(null);

        pnSegments.setLayout(new javax.swing.BoxLayout(pnSegments, javax.swing.BoxLayout.Y_AXIS));
        spSegments.setViewportView(pnSegments);

        javax.swing.GroupLayout pnResultsLayout = new javax.swing.GroupLayout(pnResults);
        pnResults.setLayout(pnResultsLayout);
        pnResultsLayout.setHorizontalGroup(
            pnResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spSegments, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnResultsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbSegmentsCount)
                .addContainerGap())
        );
        pnResultsLayout.setVerticalGroup(
            pnResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnResultsLayout.createSequentialGroup()
                .addComponent(spSegments, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbSegmentsCount))
        );

        jSplitPane1.setRightComponent(pnResults);

        javax.swing.GroupLayout pnCenterLayout = new javax.swing.GroupLayout(pnCenter);
        pnCenter.setLayout(pnCenterLayout);
        pnCenterLayout.setHorizontalGroup(
            pnCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );
        pnCenterLayout.setVerticalGroup(
            pnCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );

        pnStatusBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnStatusBar.setPreferredSize(new java.awt.Dimension(380, 21));

        lbDocsCount.setText("lbDocsCount");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lbTopicsExtracted.setText("lbTopicsExtracted");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lbAlgorithm.setText("lbAlgorithm");

        javax.swing.GroupLayout pnStatusBarLayout = new javax.swing.GroupLayout(pnStatusBar);
        pnStatusBar.setLayout(pnStatusBarLayout);
        pnStatusBarLayout.setHorizontalGroup(
            pnStatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnStatusBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbDocsCount)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTopicsExtracted)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbAlgorithm)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnStatusBarLayout.setVerticalGroup(
            pnStatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnStatusBarLayout.createSequentialGroup()
                .addGap(0, 2, Short.MAX_VALUE)
                .addGroup(pnStatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(pnStatusBarLayout.createSequentialGroup()
                        .addGroup(pnStatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbAlgorithm)
                            .addComponent(lbTopicsExtracted)
                            .addGroup(pnStatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lbDocsCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jSeparator1)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jToolBar1.setRollover(true);

        jMenu.setText("Manutenção");

        miAddToTheBase.setText("Adicionar Documentos");
        miAddToTheBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miAddToTheBaseActionPerformed(evt);
            }
        });
        jMenu.add(miAddToTheBase);

        imExtractTopics.setText("Extrair Tópicos");
        imExtractTopics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imExtractTopicsActionPerformed(evt);
            }
        });
        jMenu.add(imExtractTopics);

        imShowSegments.setText("Exibir Segmentos");
        imShowSegments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imShowSegmentsActionPerformed(evt);
            }
        });
        jMenu.add(imShowSegments);

        imShowTree.setText("Exibir Tópicos");
        imShowTree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imShowTreeActionPerformed(evt);
            }
        });
        jMenu.add(imShowTree);

        jMenuBar1.add(jMenu);

        imConfig.setText("Configurações");

        imConfigSegmentadores.setText("Configurar Segmentadores");
        imConfigSegmentadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imConfigSegmentadoresActionPerformed(evt);
            }
        });
        imConfig.add(imConfigSegmentadores);

        imConfgExtratores.setText("Configurar Extratores");
        imConfgExtratores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imConfgExtratoresActionPerformed(evt);
            }
        });
        imConfig.add(imConfgExtratores);

        imNumDescriptors.setText("Num Descritores");
        imNumDescriptors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imNumDescriptorsActionPerformed(evt);
            }
        });
        imConfig.add(imNumDescriptors);

        jMenuBar1.add(imConfig);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnStatusBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)
                    .addComponent(pnCenter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnQuery, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 155, Short.MAX_VALUE)
                .addComponent(pnQuery, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnCenter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnStatusBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
        private void defaultConfiguration(){
        
        TopicExtractionConfiguration configuration = new TopicExtractionConfiguration();

        configuration.setDirEntrada(MeetingMiner.getArfFolder().getAbsolutePath());
        configuration.setDirSaida  (MeetingMiner.getOutFolder().getAbsolutePath());
        
//      configuration.setKMeans(true); 
        configuration.setPLSA(true);
//      configuration.setBisectingKMeans(true);

        if (true) {

            configuration.setAutoNumTopics(false);

            ArrayList<Integer> numTopics = new ArrayList<>();
            
//            numTopics.add(10);
            numTopics.add(50);
            
            
//            ListModel model = lNumTopics.getModel();
//            for(int item = 0;item<model.getSize();item++){
//                numTopics.add(Integer.parseInt(model.getElementAt(item).toString()));
//            }

            configuration.setNumTopics(numTopics);
        }
        
        MeetingMiner.setTopicExtractionconfiguration(configuration);
    }
        
    private void configureTopicExtraction() {
        TopicExtractionConfiguration configuration = MeetingMiner.getTopicExtractionconfiguration();
        
//        System.out.println(String.format("Num Topics = %d", configuration.getNumTopic(0)));
        
        int num = configuration.getNumTopic(0);
        String ans = JOptionPane.showInputDialog("Digite o número de Tópicos a serem extraídos: ", num);
        
        num = Integer.parseInt(ans);
        configuration.getNumTopics().set(0, num);
    
        
        configuration.setAutoNumTopics(true);
        
        System.out.println(String.format("Num Topics = %d --> apos cofiguração pelo usuário", configuration.getNumTopic(0)));
    }

    
    	private int addToTheBase() {
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File("./.."));
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		
	 	int option =  fc.showOpenDialog(null);
	 	if (option != JFileChooser.APPROVE_OPTION) return 0;
	 	
		File folder = fc.getSelectedFile();
		
//		=========================================================================
		
		Files.prepareBaseFolders();
		int filesAdded = Files.addToTheBase(folder);
		Files.extractTextToTheBase();
		
                Segmenter segmenter = MeetingMiner.getBestSegmenterConfiguration().buildSegmenter();

		
		int segmentsExtracteds = Files.extractSegmentsToTheBase(segmenter);
		
		ShowStatus.setMessage("Concluído!");
		ShowStatus.setMessage(filesAdded + " arquivos adicionados a base");
		ShowStatus.setMessage(segmentsExtracteds + " segmentos extratídos");
		
		return filesAdded;
	}
        
        
        	private void setControlsEnable() {
		imExtractTopics     .setEnabled(isArffFileFound);
		imConfgExtratores             .setEnabled(isArffFileFound);
//		lbDescriptorsByTopic.setEnabled(isArffFileFound);
//		cbDescriptorsByTopic.setEnabled(isArffFileFound);
		
		boolean wasTopicsExtactionExecuted = isMatricesFound;
		
		imShowSegments.setEnabled(wasTopicsExtactionExecuted);
		imShowTree    .setEnabled(wasTopicsExtactionExecuted);
		
		UIUtils.setEnable(pnQuery, wasTopicsExtactionExecuted);
	}

        
        	private void verifyStatus() {
		
		isBasesFolderFound = Files.getBasesFolder().exists();
		isOrignalDocsFound = Files.getOriginalDocs().exists();
		if (isOrignalDocsFound) originalDocsCount = Files.getOriginalDocs().list().length;
		isArffFileFound    = MeetingMiner.getArffFile().exists();
		isMatricesFound    = MeetingMiner.getDocument_TopicMatrixFile().exists() && MeetingMiner.getTerm_TopicMatrixFile().exists();
		
		ShowStatus.setMessage(isBasesFolderFound ? "Diretório base econtrado" : "Diretório base não econtrado");
		ShowStatus.setMessage(originalDocsCount > 0 ? String.format("%d documentos encontrados", originalDocsCount) : "Nenhum documento encontrado");
		ShowStatus.setMessage(isArffFileFound ? "Arquivo arff encontrado" : "Arquivo arff não encontrado");
		ShowStatus.setMessage(isMatricesFound ? "Matrizes encontradas" : "Matrizes não encontradas");
		
		if(isMatricesFound) {
			MeetingMiner.extractDescriptorsAndFiles();
		}
		
		if (isMatricesFound) {
			lbTopicsExtracted.setText(String.format("%d topics extracted", MeetingMiner.getNumTopics()));
			
			Properties topicExtractionConfigSaveFile = new Properties();
			try {
				topicExtractionConfigSaveFile.load(new FileInputStream(MeetingMiner.getConfigSaveFile()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			lbAlgorithm.setText(topicExtractionConfigSaveFile.getProperty("Algorithm"));
		}
		else {
			lbTopicsExtracted.setText(String.format("%d topics extracted", MeetingMiner.getNumTopics()));
			
			if (MeetingMiner.getConfigSaveFile().exists())
				lbAlgorithm.setText("Please, extract the topics");
			
		}
		
		lbDocsCount.setText(String.format("%d documentos na base de dados", originalDocsCount));
		
		
		setControlsEnable();
	}

        
	private void setWainting(boolean waiting) {
		UIUtils.setWaiting(new Container[] {pnCtrlQuery, pnCenter}, waiting);
		
		if (!waiting){
			verifyStatus();
		}
	}

    
    private void miAddToTheBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAddToTheBaseActionPerformed
        
        		new Thread() {
			        @Override
			        public void run(){
			        	setWainting(true);
			        	if (addToTheBase() > 0 ) {
			        		MeetingMiner.represent(true);
			        		MeetingMiner.removePreviousMatrices();
			        	}
			    		setWainting(false);
			        }
			}.start();

        
    }//GEN-LAST:event_miAddToTheBaseActionPerformed

    
    	private void extractTopics() {
		
//		MeetingMiner.prepareFolders();
//		defaultConfiguration();
                configureTopicExtraction();
		MeetingMiner.miningTheMeetings();
	}

    
    private void imExtractTopicsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imExtractTopicsActionPerformed

        		new Thread() {
			        @Override
			        public void run(){
			        	setWainting(true);
			        	extractTopics();
			    		setWainting(false);
			        }
				}.start();  


    }//GEN-LAST:event_imExtractTopicsActionPerformed

    private void showTopicTree() {
        
        MeetingMiner.prepareFolders();
	MeetingMiner.extractDescriptorsAndFiles();
        
        DefaultTreeModel model = new DefaultTreeModel(MeetingMiner.createTree());
        
        jtTopics.setModel(model);

    }
    
    private void imShowTreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imShowTreeActionPerformed

        showTopicTree();
        
    }//GEN-LAST:event_imShowTreeActionPerformed

    
    private ArrayList<Segment> filterSegmentsBySegmentFile(File segmentFile) {
        ArrayList<Segment> result = new ArrayList<>();
        
        return result;
    }
    
    	private ArrayList<MMTopic> filterTopicsByDescriptor(ArrayList<MMTopic> topics, Set<String> descs) {
		ArrayList<MMTopic> result = new ArrayList<>();
		
		for(MMTopic t : topics) {
//			ArrayList<String> descriptorsIntersections = t.descriptorsIntersection(descs);
			Set<String> descriptorsIntersections = t.descriptorsStemedIntersection(descs);
			if(descriptorsIntersections.size() > 0) {
				result.add(t);
			}
		}
		
		return result;
	}

    	private void clearSegments() {
		pnSegments.removeAll();
		pnSegments.revalidate();
		pnSegments.repaint();
	}
	
       	private void addSegmentPanel(Segment seg) {
		PnSegment pnSeg = new PnSegment(seg); 
		
		pnSegs.add(pnSeg);
		
//		int w = pnSegments.getWidth()-20;
		int w = 800;
		int h = 120;		
		pnSeg.setPreferredSize(new Dimension(w, h));
		pnSeg.setMaximumSize(new Dimension(w, h));
	
		pnSegments.add(pnSeg);
		pnSegments.revalidate();
		pnSegments.repaint();
	}

        private JPanel createPnSegments() {
		JPanel pnSegments = new JPanel();

		pnSegments.setLayout(new BoxLayout(pnSegments, BoxLayout.Y_AXIS));

		spSegments = new JScrollPane(pnSegments);

		spSegments.setBorder(new CompoundBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), new EmptyBorder(5, 5, 5, 5) ));
		spSegments.getVerticalScrollBar().setUnitIncrement(12);

		return pnSegments;
	}
        
        
        private void showSegmentsByTreeNode(DefaultMutableTreeNode selectedNode ) {
	
           ArrayList<File> files = new ArrayList<>();

           if(selectedNode.isLeaf()) {
                    files.add(new File(Files.getSegmentedDocs()+"/"+selectedNode.toString()));                      
           } else {
                int childCount = selectedNode.getChildCount();
           
                for(int i=0;i<childCount;i++) {
                    files.add(new File(Files.getSegmentedDocs()+"/"+selectedNode.getChildAt(i).toString()));
                }
           }

           
//           for(File f : files) {
//               System.out.println(String.format("Child File --> %b - %s", f.exists(), f));
//           }
           
           
//           if(false) {
               
                MeetingMiner.extractDescriptorsAndFiles();
		ArrayList<MMTopic> topics = MeetingMiner.getMMTopics();
//		ArrayList<Segment> segments = null;
		
                /** Carrega todos os segmentos que contém Documentos associados com todos os tópicos */
                
                
//		segments = Segment.getAllSegments(topics);
		ArrayList<Segment> segments = Segment.getSegmentsByFiles(topics, files);
                
                clearSegments();
		lbSegmentsCount.setText("Gerando visualização");
		ShowStatus.setMessage  ("Gerando visualização");
		int count = 0;
		for(Segment seg : segments) {
			addSegmentPanel(seg);
			count++;
		}
		lbSegmentsCount.setText(count+" trechos relacionados");
		ShowStatus.setMessage  (count+" trechos relacionados");
//           }

                

        }


    	private void showSegments(boolean filter) {
		MeetingMiner.prepareFolders();	
		
		MeetingMiner.extractDescriptorsAndFiles();
		ArrayList<MMTopic> topics = MeetingMiner.getMMTopics();
		
		ArrayList<Segment> segments = null;
		
		if (filter) {
			
			HashMap<String, String> userDescsStems = new HashMap<>();
			for(String s : tfQuery.getText().split(" ")) {
				if (!Preprocess.getStopWords().isStopWord(s)) {
					userDescsStems.put(s, Preprocess.getStemmer().wordStemming(s));
				}
			}

			/** Filtra os tópicos que contém algum descritor informado pelo usuário */
			ShowStatus.setMessage("\nFiltering topics by: " + userDescsStems.values());
			topics = filterTopicsByDescriptor(topics, userDescsStems.keySet());

			/** Carrega os (objetos) segmentos que contém Documentos(segmento de uma ata) associados com os tópicos filtrados */
			/** Para cada tópico associado ao segmento, associa os decritores dos tópico ao segmento */
			segments = Segment.getAllSegments(topics);
			
			for(Segment seg : segments) {
				seg.matchUserDescriptors(userDescsStems.keySet());
			}
			Segment.sortSegmentsByMatcheCount(segments);
			
			ShowStatus.setMessage(String.format("%d tópicos selecionados %d segmentos encontrados", topics.size(), segments.size()));
		}
		else {

			/** Carrega todos os segmentos que contém Documentos associados com todos os tópicos */
			segments = Segment.getAllSegments(topics);
		}
			
		
		clearSegments();
		lbSegmentsCount.setText("Gerando visualização");
		ShowStatus.setMessage  ("Gerando visualização");
		int count = 0;
		for(Segment seg : segments) {
			addSegmentPanel(seg);
			count++;
		}
		lbSegmentsCount.setText(count+" trechos relacionados");
		ShowStatus.setMessage  (count+" trechos relacionados");

	}

    
    
    private void imShowSegmentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imShowSegmentsActionPerformed

				new Thread() {
			        @Override
			        public void run(){
			        	setWainting(true);
			        	showSegments(false);
			    		setWainting(false);
			        }
				}.start();

        
    }//GEN-LAST:event_imShowSegmentsActionPerformed

    private void imNumDescriptorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imNumDescriptorsActionPerformed
        int num = MeetingMiner.getDescriptorsByTopic();
        
        String ans = JOptionPane.showInputDialog("Digige o número de descritores: ", num);
        System.out.println("ans = "+ans);

        if(ans != null) {
            num = Integer.parseInt(ans);
            MeetingMiner.setDescriptorsByTopic(num);
        }
    }//GEN-LAST:event_imNumDescriptorsActionPerformed

    private void jtTopicsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtTopicsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtTopicsMouseClicked

    private void btExplorarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExplorarActionPerformed
        new Thread() {
            @Override
            public void run(){
                setWainting(true);
                showSegments(true);
                setWainting(false);
            }
        }.start();
    }//GEN-LAST:event_btExplorarActionPerformed

    private void imConfgExtratoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imConfgExtratoresActionPerformed
        FrConfigExtractor f = new FrConfigExtractor(MeetingMiner.getTopicExtractionconfiguration());
        f.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_imConfgExtratoresActionPerformed

    private void imConfigSegmentadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imConfigSegmentadoresActionPerformed
        FrConfigSegmenter f = new FrConfigSegmenter(MeetingMiner.getTopicExtractionconfiguration());
        f.setVisible(true);
    }//GEN-LAST:event_imConfigSegmentadoresActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btExplorar;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.JMenuItem imConfgExtratores;
    private javax.swing.JMenu imConfig;
    private javax.swing.JMenuItem imConfigSegmentadores;
    private javax.swing.JMenuItem imExtractTopics;
    private javax.swing.JMenuItem imNumDescriptors;
    private javax.swing.JMenuItem imShowSegments;
    private javax.swing.JMenuItem imShowTree;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTree jtTopics;
    private javax.swing.JLabel lbAlgorithm;
    private javax.swing.JLabel lbDocsCount;
    private javax.swing.JLabel lbSegmentsCount;
    private javax.swing.JLabel lbTopicsExtracted;
    private javax.swing.JMenuItem miAddToTheBase;
    private javax.swing.JPanel pnCenter;
    private javax.swing.JPanel pnCtrlQuery;
    private javax.swing.JPanel pnQuery;
    private javax.swing.JPanel pnResults;
    private javax.swing.JPanel pnSearchMode;
    private javax.swing.JPanel pnSegments;
    private javax.swing.JPanel pnStatusBar;
    private javax.swing.JPanel pnTree;
    private javax.swing.JRadioButton rbFiltrarTopicos;
    private javax.swing.JRadioButton rbRankPorConteudo;
    private javax.swing.JScrollPane spSegments;
    private javax.swing.JTextField tfQuery;
    // End of variables declaration//GEN-END:variables
}
