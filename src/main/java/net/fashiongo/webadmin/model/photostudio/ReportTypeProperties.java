package net.fashiongo.webadmin.model.photostudio;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jinwoo on 2019. 4. 25..
 */
@Setter
@Getter
public class ReportTypeProperties {

    private Map<String, List<Integer>> categoryIdMap = new HashMap<>();;

    private Map<String, List<Integer>> packageIdMap = new HashMap<> ();;

}
