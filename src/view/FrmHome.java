
package view;

import helpers.IOAdmin;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javazoom.jl.decoder.JavaLayerException;
import Music.MetadataAdmin;
import Music.MusicPlay;
import Music.Status;
import dao.MusicDAO;
import dao.PerfilDAO;
import entity.Music;
import entity.Perfil;
import helpers.ExceptionLogger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.MusicPlayTabelModel;

public class FrmHome extends javax.swing.JFrame {

    public final String SONGSPATH = IOAdmin.PROJECTPATH + "\\msc";
    private MusicDAO daoMusic;
    private PerfilDAO daoPerfil;
    private ArrayList<Music> data;
    private MusicPlayTabelModel tableModel;
    private MusicPlay player;
    private Thread threadPg;
    private int indicePg = 0;
    private double valorUmPorcento;
    private int currentIndex = 0;
    private boolean firstPlay = true;
    private boolean playing = false;

    public FrmHome() {
        initComponents();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                close();
            }
        });
        this.setLocationRelativeTo(null);
        jScrollPane1.getViewport().setBackground(Color.BLACK);

        daoPerfil = new PerfilDAO();
        daoMusic = new MusicDAO();
        data = daoMusic.getAll();
        tableModel = new MusicPlayTabelModel(data);
        cmbPerfil.setModel(new DefaultComboBoxModel<>(new Vector(daoPerfil.getAll())));
        cmbPerfil.setSelectedIndex(-1);
        tblMusic.setModel(tableModel);
        FileInputStream input;
        try {
            if (tableModel.getRowCount() > 0) {
                input = new FileInputStream(new File(tableModel.getMusic(currentIndex).getCaminho()));
                player = new MusicPlay(input);
            }

        }
        catch (FileNotFoundException | JavaLayerException ex) {
            ExceptionLogger.saveLogException(ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnlPlay2 = new javax.swing.JPanel();
        btnPlay = new javax.swing.JToggleButton();
        btnNext = new javax.swing.JToggleButton();
        btnForward = new javax.swing.JToggleButton();
        jDesktopPane1 = new javax.swing.JDesktopPane(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        jPanel3 = new javax.swing.JPanel();
        pgMusic = new javax.swing.JProgressBar();
        jScrollPane1 = new javax.swing.JScrollPane(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        }
        ;
        tblMusic = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        cmbPerfil = new javax.swing.JComboBox<>();
        txtFiltrer = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        barraMenu = new javax.swing.JMenuBar(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        menuPerfil = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem()
        ;
        menuImportMusic = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        menuMusic = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("JListen");
        setBackground(new java.awt.Color(0, 0, 0));
        setExtendedState(MAXIMIZED_BOTH);
        setLocation(new java.awt.Point(0, 0));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(684, 363));

        pnlPlay2.setBackground(new java.awt.Color(0, 0, 0));
        pnlPlay2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/play-button.png"))); // NOI18N
        btnPlay.setBorderPainted(false);
        btnPlay.setContentAreaFilled(false);
        btnPlay.setFocusPainted(false);
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/fast-forward.png"))); // NOI18N
        btnNext.setBorderPainted(false);
        btnNext.setContentAreaFilled(false);
        btnNext.setFocusPainted(false);
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextjToggleButton1ActionPerformed(evt);
            }
        });

        btnForward.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/rewind.png"))); // NOI18N
        btnForward.setBorderPainted(false);
        btnForward.setContentAreaFilled(false);
        btnForward.setFocusPainted(false);
        btnForward.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnForwardjToggleButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPlay2Layout = new javax.swing.GroupLayout(pnlPlay2);
        pnlPlay2.setLayout(pnlPlay2Layout);
        pnlPlay2Layout.setHorizontalGroup(
            pnlPlay2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPlay2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnForward, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlPlay2Layout.setVerticalGroup(
            pnlPlay2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPlay2Layout.createSequentialGroup()
                .addGroup(pnlPlay2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnForward, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 790, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel3.setOpaque(false);

        pgMusic.setBackground(new java.awt.Color(0, 0, 0));
        pgMusic.setBorderPainted(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(pgMusic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 8, Short.MAX_VALUE)
                .addComponent(pgMusic, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pgMusic.setStringPainted(true);
        pgMusic.setForeground(Color.BLUE);
        pgMusic.setString("10%");

        jScrollPane1.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        jScrollPane1.setForeground(new java.awt.Color(204, 204, 204));
        jScrollPane1.setToolTipText("");
        jScrollPane1.setOpaque(false);

        tblMusic.setBackground(new java.awt.Color(0, 0, 0));
        tblMusic.setForeground(new java.awt.Color(204, 204, 204));
        tblMusic.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Título 1"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMusic.setGridColor(new java.awt.Color(0, 0, 0));
        tblMusic.setSelectionForeground(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(tblMusic);
        tblMusic.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    try {
                        player.close();
                        indicePg = 0;
                        playNewSong(table.getSelectedRow());
                    }
                    catch (FileNotFoundException | JavaLayerException ex) {
                        ExceptionLogger.saveLogException(ex);
                        JOptionPane.showMessageDialog(null, "Erro ao tocar nova música");
                    }
                }
            }
        });

        tblMusic.setSelectionBackground(new Color(255, 112, 56));
        tblMusic.setSelectionForeground(new Color(0, 0, 0));

        jPanel2.setOpaque(false);

        cmbPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbPerfil.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                filtrerSongs();
            }
        });

        txtFiltrer.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrerSongs();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrerSongs();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrerSongs();
            }
        });

        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Filtre por perfil ou por algo sobre a música");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtFiltrer)
                        .addComponent(cmbPerfil, 0, 233, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtFiltrer, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPlay2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jDesktopPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE))
                    .addComponent(jDesktopPane1))
                .addGap(11, 11, 11)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlPlay2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        barraMenu.setBackground(new java.awt.Color(0, 0, 0));
        barraMenu.setBorder(null);
        barraMenu.setToolTipText("");
        barraMenu.setAutoscrolls(true);
        barraMenu.setName(""); // NOI18N
        barraMenu.setOpaque(false);
        barraMenu.setVerifyInputWhenFocusTarget(false);

        menuPerfil.setBackground(new java.awt.Color(0, 0, 0));
        menuPerfil.setForeground(new java.awt.Color(153, 153, 153));
        menuPerfil.setText("Perfis");
        menuPerfil.setOpaque(true);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/853249.png"))); // NOI18N
        jMenuItem1.setText("Perfil");
        jMenuItem1.setContentAreaFilled(false);
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuPerfil.add(jMenuItem1);

        barraMenu.add(menuPerfil);

        menuImportMusic.setBackground(new java.awt.Color(0, 0, 0));
        menuImportMusic.setForeground(new java.awt.Color(153, 153, 153));
        menuImportMusic.setText("Músicas");
        menuImportMusic.setOpaque(true);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/music-document-upload.png"))); // NOI18N
        jMenuItem2.setText("Importar Músicas");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menuImportMusic.add(jMenuItem2);

        menuMusic.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        menuMusic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/music-player.png"))); // NOI18N
        menuMusic.setText("Musicas Importadas");
        menuMusic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuMusicActionPerformed(evt);
            }
        });
        menuImportMusic.add(menuMusic);

        barraMenu.add(menuImportMusic);

        setJMenuBar(barraMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1039, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
        );

        setBounds(0, 0, 1055, 529);
    }// </editor-fold>//GEN-END:initComponents

    private void btnForwardjToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnForwardjToggleButton1ActionPerformed
        forward();
    }//GEN-LAST:event_btnForwardjToggleButton1ActionPerformed

    private void btnNextjToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextjToggleButton1ActionPerformed
        next();
    }//GEN-LAST:event_btnNextjToggleButton1ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        int selectRow = tblMusic.getSelectedRow() == -1 ? 0 : tblMusic.getSelectedRow();
        if (player != null) {
            try {
                if (player.getStatus() == Status.NOTSTARTED || player.getStatus() == Status.CLOSED) {
                    playNewSong(selectRow);
                }
                else if (player.getStatus() == Status.PLAYING) {
                    player.pause();
                    playing = false;
                    btnPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/play-button.png")));
                }
                else if (player.getStatus() == Status.STOPPED) {
                    player.resume();
                    playing = true;
                    btnPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pause.png")));
                }
            }
            catch (Exception e) {
                ExceptionLogger.saveLogException(e);
                throw new RuntimeException(e);
            }
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        IFramePerfilTable ifp = new IFramePerfilTable(tableModel, cmbPerfil);
        jDesktopPane1.add(ifp);
        ifp.centralizarFrame();
        ifp.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed

        File arquivo[];
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Selecione as músicas que deseja importar");
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.setMultiSelectionEnabled(true);
        jfc.addChoosableFileFilter(new FileNameExtensionFilter(
                "Arquivos de Música(*.mp3)", "mp3"));
        jfc.setAcceptAllFileFilterUsed(false);

        int returnValue = jfc.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            MetadataAdmin metadata = new MetadataAdmin();
            arquivo = jfc.getSelectedFiles();
            Music music = null;
            for (File file : arquivo) {
                try {
                    music = metadata.GetMetaDados(file, SONGSPATH);
                    IOAdmin.copy(file, new File(music.getCaminho()));
                    daoMusic.add(music);
                    this.tableModel.addMusic(music);
                }
                catch (IOException ex) {
                    ExceptionLogger.saveLogException(ex);
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }

            }
            JOptionPane.showMessageDialog(jDesktopPane1, "Músicas importadas com sucesso!");
        }

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void menuMusicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMusicActionPerformed
        IFrameMusicTable ifm = new IFrameMusicTable();
        jDesktopPane1.add(ifm);
        ifm.centralizarFrame();
        ifm.setVisible(true);
    }//GEN-LAST:event_menuMusicActionPerformed

    private void close() {
        int c = JOptionPane.showConfirmDialog(this, "Deseja realmente fechar?", "Fechar", JOptionPane.YES_NO_OPTION);
        if (c == JOptionPane.YES_OPTION) {
            if (this.player != null) {
                this.player.close();
                this.player = null;
            }
            this.indicePg = 100;
            this.dispose();
        }
    }

    private void playNewSong(int selectRow) throws FileNotFoundException, JavaLayerException {
        Music musicNow = tableModel.getMusic(selectRow);
        FileInputStream input = new FileInputStream(new File(musicNow.getCaminho()));
        player = new MusicPlay(input);
        double total = tableModel.getMusic(selectRow).getDuracao() / 1000;
        double valorUmPorcent = total / 100;

        player.play();
        playing = true;
        this.indicePg = 0;
        this.valorUmPorcento = valorUmPorcent;
        currentIndex = selectRow;
        tblMusic.setRowSelectionInterval(currentIndex, currentIndex);
        if (firstPlay || indicePg == 100) {
            this.firstPlay = false;
            this.indicePg = 0;
            this.startPgThread();
        }

        btnPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pause.png")));
    }

    private void next() {
        if ((currentIndex + 1) < tableModel.getRowCount()) {
            try {
                this.player.close();
                indicePg = 0;
                playNewSong(currentIndex + 1);
            }
            catch (FileNotFoundException | JavaLayerException ex) {
                ExceptionLogger.saveLogException(ex);
                JOptionPane.showMessageDialog(this, "Erro ao tocar nova música");
            }
        }
    }

    private void forward() {
        if ((currentIndex - 1) >= 0) {
            try {
                this.player.close();
                indicePg = 0;
                playNewSong(currentIndex - 1);
            }
            catch (FileNotFoundException | JavaLayerException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao tocar nova música");
            }
        }
    }

    private void startPgThread() {

        this.threadPg = new Thread(() -> {
            while (indicePg <= 100) {
                if (playing) {
                    pgMusic.setValue(indicePg);
                    try {
                        Thread.sleep((long) (valorUmPorcento * 1000));
                    }
                    catch (InterruptedException ex) {
                        JOptionPane.showMessageDialog(this, "Problemas na thread de atualização da progress bar");
                    }
                    if (indicePg == 100) {
                        btnPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/play-button.png")));
                    }
                    indicePg++;
                }
            }

        });
        this.threadPg.start();
    }

    private void filtrerSongs() {
        data = daoMusic.getAllByGenero((Perfil) cmbPerfil.getSelectedItem(), txtFiltrer.getText());
        tableModel = new MusicPlayTabelModel(data);
        tblMusic.setModel(tableModel);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ExceptionLogger.saveLogException(ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JToggleButton btnForward;
    private javax.swing.JToggleButton btnNext;
    private javax.swing.JToggleButton btnPlay;
    private javax.swing.JComboBox<String> cmbPerfil;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu menuImportMusic;
    private javax.swing.JMenuItem menuMusic;
    private javax.swing.JMenu menuPerfil;
    private javax.swing.JProgressBar pgMusic;
    private javax.swing.JPanel pnlPlay2;
    private javax.swing.JTable tblMusic;
    private javax.swing.JTextField txtFiltrer;
    // End of variables declaration//GEN-END:variables
}