package com.block.entity;

import com.block.tx.entity.Tx;
import com.msg.IsUpdate;
import com.supernode.entity.SuperNode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Block {

    @GeneratedValue
    @Id
    private Long id;
    //所查询区块的hash
    private String blockHash;
    //所查询区块的高度
    private String blockHeight;
    //当前区块中的tx的笔数
    private Integer numbersOfTx;
    //当前区块的父块的hash
    private String previous_block_hash;
    //当前查询的区块的大小
    private Integer sizeOfBlock;
    //当前查询的区块的区块头大小
    private Integer sizeOfBlockHeader;
    //当前区块的生成时间的时间戳
    private Long timeStamp;
    //当前区块中所包含的所有tx的id
    @OneToMany(cascade = CascadeType.ALL)
    private List<Tx> txs;


    public Block() {
    }

    public Block(String blockHash, String blockHeight, String previous_block_hash, Integer sizeOfBlock, Integer sizeOfBlockHeader, Long timeStamp) {
        this.blockHash = blockHash;
        this.blockHeight = blockHeight;
        this.previous_block_hash = previous_block_hash;
        this.sizeOfBlock = sizeOfBlock;
        this.sizeOfBlockHeader = sizeOfBlockHeader;
        this.timeStamp = timeStamp;
        this.numbersOfTx = 0;
    }

    public Long getId() {
        return id;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public String getBlockHeight() {
        return blockHeight;
    }

    public Integer getNumbersOfTx() {
        return numbersOfTx;
    }

    public String getPrevious_block_hash() {
        return previous_block_hash;
    }

    public Integer getSizeOfBlock() {
        return sizeOfBlock;
    }

    public Integer getSizeOfBlockHeader() {
        return sizeOfBlockHeader;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public List<Tx> getTxs() {
        List<Tx> txList = new ArrayList<>();
        for (Tx tx : txs) {
            if (IsUpdate.alreadyUpdate(tx.getIsUpdate())){
                txList.add(tx);
            }
        }
        return txList;
    }

    public void setTxs(List<Tx> txs) {
        this.txs = txs;
    }

    public void addTxSize() {
        this.numbersOfTx++;
    }

    @Override
    public String toString() {
        return "Block{" +
                "blockHash='" + blockHash + '\'' +
                ", blockHeight='" + blockHeight + '\'' +
                '}';
    }
}
