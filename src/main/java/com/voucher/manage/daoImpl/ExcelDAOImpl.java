package com.voucher.manage.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.voucher.manage.dao.ExcelDAO;

public class ExcelDAOImpl extends JdbcDaoSupport implements ExcelDAO{

	@Override
	public String insertTable(Map<String, String> jsonMap, List<List<String>> excelLists) {
		// TODO Auto-generated method stub

		List allTable = new ArrayList<>();

		for (Map.Entry<String, String> entry : jsonMap.entrySet()) {

			String Key = entry.getKey();
			String entrys = entry.getValue();

			List<String> entryList = Arrays.asList(StringUtils.strip(entrys, "[]"));

			String ay = entryList.get(0);

			String[] ayStrings = ay.split("},");

			String REGEX = "}";
			Pattern pattern = Pattern.compile(REGEX);

			List<String> intos = new ArrayList<>();

			List<Object> values = new ArrayList<>();

			for (String ayString : ayStrings) {

				Matcher matcher = pattern.matcher(ayString);
				if (!matcher.find())
					ayString = ayString + "}";

				Map<String, String> rowMap = JSONObject.parseObject(ayString, new TypeReference<Map<String, String>>() {
				});

				if (rowMap.get("type") != null) {

					Map<Integer, Object> map = new HashMap<>();

					if (rowMap.get("type").equals("column")&&rowMap.get("value")!=null&&!rowMap.get("value").equals("")) {

						intos.add(rowMap.get("column"));

						map.put(0, Integer.parseInt(rowMap.get("value")));

						values.add(map);

						if (rowMap.get("genre")!=null&&!rowMap.get("genre").equals("")){
							map.put(3, rowMap.get("column"));
						}
						
					} else if (rowMap.get("type").equals("hash")) {

						intos.add(rowMap.get("column"));

						map.put(1, "hash");

						if (rowMap.get("foreignTable")!=null){
							map.put(2, rowMap.get("foreignTable"));							
						}

						if (rowMap.get("foreignKey")!=null){
							map.put(3, rowMap.get("foreignKey"));							
						}else{
							map.put(3, rowMap.get("column"));
						}
						
						values.add(map);

					}
					
					if(rowMap.get("genre")!=null&&!rowMap.get("genre").equals("")){
						int genre=Integer.parseInt(rowMap.get("genre"));
						if(genre==1){
							map.put(4, genre);
						}
					}
					
				}

			}

			if (intos.size() != 0) {
				Map map = new HashMap<>();
				map.put("tableName", Key);
				map.put("intos", intos);
				map.put("values", values);
				allTable.add(map);
			}

		}

		Iterator<Map<String, Object>> iterator = allTable.iterator();

		Map intoMap=new HashMap<>();
		
		Map hash = new HashMap<>();
		
		while (iterator.hasNext()) {

			Map<String, Object> tableMap = iterator.next();

			String tableName = (String) tableMap.get("tableName");

			List<String> intos = (List) tableMap.get("intos");

			List values = (List) tableMap.get("values");

			Iterator<String> intoIterator = intos.iterator();

			String into = "";

			int i=0;
			
			while (intoIterator.hasNext()) {

				if (into.equals(""))
					into = into + "(";

				String intovalue = intoIterator.next();

				into = into + intovalue + ",";

				Map<Integer, Object> valueMap = (Map<Integer, Object>) values.get(i);

				if (valueMap.get(1) != null && valueMap.get(2) != null) {
					String foreignTable = (String) valueMap.get(2);
					String foreignkey = intovalue;
					Map<String, Object> map = new HashMap<>();
					map.put("oneselfTable", tableName);
					map.put("foreignTable", valueMap.get(2));
					map.put("foreignkey", intovalue);
					Map<String, Object> thistable=(Map) hash.get(tableName);
					if(thistable==null)
						thistable=new HashMap<String, Object>();
					thistable.put(UUID.randomUUID().toString(), map);
					hash.put(tableName, thistable);
				}

				i++;
			}

			into = into.substring(0, into.length() - 1) + ")";

			intoMap.put(tableName, into);
			
		}

		
		for (List<String> excel : excelLists) {

			iterator = allTable.iterator();

			Map<String, Object> outHash=new HashMap<>();
			
			Map<String, Object> inHash=new HashMap<>();
			
			Map<String, Object> notRepeatHash=new HashMap<>();
			
			while (iterator.hasNext()) {

				Map<String, Object> tableMap = iterator.next();

				String tableName = (String) tableMap.get("tableName");

				List values = (List) tableMap.get("values");

				Iterator<Map<Integer, Object>> valueIterator = values.iterator();

				String value = "";

				while (valueIterator.hasNext()) {

					if (value.equals(""))
						value = value + "(";

					Map<Integer, Object> valueMap = valueIterator.next();

					if (valueMap.get(0) != null) {
						String v = String.valueOf(valueMap.get(0));
						value = value + "'"+excel.get(Integer.parseInt(v)) + "',";
						
						if(valueMap.get(4) != null){
							Map<String, Object> notRepeat=new HashMap<>();
							notRepeat.put((String) valueMap.get(3), excel.get(Integer.parseInt(v)));
							Map<String, Object> notRepeats=new HashMap<>();
							notRepeats.put(UUID.randomUUID().toString(), notRepeat);
							notRepeatHash.put(tableName, notRepeats);
						}
						
					} else if (valueMap.get(1) != null) {
						String v = UUID.randomUUID().toString();
						value = value + "'" + v + "',";
						Map<String, Object> thisouttable=(Map) outHash.get(tableName);
						if(thisouttable==null)
							thisouttable=new HashMap<String, Object>();
						String column=String.valueOf(valueMap.get(3));
						thisouttable.put(column, v);
						outHash.put(tableName, thisouttable);
						if (valueMap.get(2) != null) {							
							Map<String, Object> keyMap = (Map<String, Object>) hash.get(tableName);
							for (Map.Entry<String, Object> entry : keyMap.entrySet()) {					
								Map<String, Object> thisTable=(Map<String, Object>) entry.getValue();
								Map<String, Object> map=(Map) inHash.get(tableName);
								if(map==null)
									map=new HashMap<String, Object>();
								Map<String, Object> thisintable=new HashMap<>();
								String foreignTable=(String) thisTable.get("foreignTable");
								String foreignkey=(String)thisTable.get("foreignkey");
								String oneselfTable=(String)thisTable.get("oneselfTable");
								thisintable.put("foreignTable", foreignTable);
								thisintable.put("foreignkey", foreignkey);
								thisintable.put("oneselfTable", oneselfTable);								
								String column2=String.valueOf(valueMap.get(3));								
								thisintable.put(column2, v);
								map.put(UUID.randomUUID().toString(), thisintable);
								inHash.put(tableName, map);
							}
						}
						
						if(valueMap.get(4) != null){
							Map<String, Object> notRepeat=new HashMap<>();
							notRepeat.put((String) valueMap.get(3), v);
							Map<String, Object> notRepeats=new HashMap<>();
							notRepeats.put(UUID.randomUUID().toString(), notRepeat);
							notRepeatHash.put(tableName, notRepeats);
						}
					}
					
					

				}

				
				String into = (String) intoMap.get(tableName);

				value = value.substring(0, value.length() - 1) + ")";

				String notWhere="";
				
				Map<String, Object> thisNotRepeat=(Map<String, Object>) notRepeatHash.get(tableName);
				
				if (thisNotRepeat!=null&&!thisNotRepeat.isEmpty()) {
					for (Map.Entry<String, Object> notentry : thisNotRepeat.entrySet()) {

						Map<String, Object> notvalues = (Map<String, Object>) notentry.getValue();

						for (Map.Entry<String, Object> notvalue : notvalues.entrySet()) {

							if (notvalue.getValue() != null) {
								notWhere = notWhere + notvalue.getKey() + " = '" + notvalue.getValue() + "' or ";
							} else {
								notWhere = notWhere + notvalue.getKey() + " is null or ";
							}

						}

					}
				}
				
				System.out.println("notWhere="+notWhere);
				
				String selectSql="";
				
				if(notWhere!=null&&!notWhere.equals(""))
					selectSql=" select count(*) from "+tableName+" where "+notWhere.substring(0, notWhere.length()-3); 

				String sql=" INSERT INTO "+tableName+" "+into+" VALUES "+value;
				
				try{
					
					int count=0;
					
					if(notWhere!=null&&!notWhere.equals(""))
						count=(int) this.getJdbcTemplate().queryForMap(selectSql).get("");
					
					if(count>0){
						System.out.println("count="+count);
						continue ;
					}
					
					this.getJdbcTemplate().execute(sql);
					
				}catch (org.springframework.dao.DataIntegrityViolationException e) {
					// TODO: handle exception
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					String message=e.getMessage();
					return message;
				}catch (org.springframework.jdbc.BadSqlGrammarException e) {
					// TODO: handle exception
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					String message=e.getMessage();
					return message;
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					String message=e.getMessage();
					return "写入失败("+message+")";
				}
				
				for (Map.Entry<String, Object> inentry : inHash.entrySet()) {

					String currentHash="";
					
					Map<String, Object> inentryValues = (Map<String, Object>) inentry.getValue();

					for (Map.Entry<String, Object> inentryValue : inentryValues.entrySet()) {

						Map<String, Object> inentr = (Map<String, Object>) inentryValue.getValue();

						String foreignTable = (String) inentr.get("foreignTable");

						for (Map.Entry<String, Object> outentry : outHash.entrySet()) {

							if (outentry.getKey().equals(foreignTable)) {

								Map<String, Object> outMap = (Map<String, Object>) outentry.getValue();

								for (Map.Entry<String, Object> out : outMap.entrySet()) {

									for (Map.Entry<String, Object> in : inentryValues.entrySet()) {

										Map<String, Object> iv = (Map<String, Object>) in.getValue();

										for (Map.Entry<String, Object> i : iv.entrySet()) {

											String thisHash=inentry.getKey()+i.getKey()+out.getValue();

											if(currentHash.equals(thisHash)){
												continue;
											}else {
												currentHash=thisHash;
											}

											if (out.getKey().equals(i.getKey())) {

												String sql2 = " update " + inentry.getKey() + " set " + i.getKey()
														+ " = '" + out.getValue() + "' where " + i.getKey() + " = '"
														+ i.getValue() + "'";

												try{
													this.getJdbcTemplate().execute(sql2);
												}catch (Exception e) {
													// TODO: handle exception
													e.printStackTrace();
													TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
													return "写入失败";
												}
																							
											}
										}
									}
								}
							}
						}
					}
				}
		
				
				
			}
			
		}

		return "写入成功";

	}

	@Override
	public String insertTable(Connection connection, Map<String, String> jsonMap, List<List<String>> excleLists) {
		// TODO Auto-generated method stub

		try {

			try {
				connection.setAutoCommit(false);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			List allTable = new ArrayList<>();

			/*
			 * 添加excel没有的字段的表名
			 */
			//String otherTable="User_License";
			
			for (Map.Entry<String, String> entry : jsonMap.entrySet()) {

				String Key = entry.getKey();
				String entrys = entry.getValue();

				List<String> entryList = Arrays.asList(StringUtils.strip(entrys, "[]"));

				String ay = entryList.get(0);

				String[] ayStrings = ay.split("},");

				String REGEX = "}";
				Pattern pattern = Pattern.compile(REGEX);

				List<String> intos = new ArrayList<>();

				List<Object> values = new ArrayList<>();

				for (String ayString : ayStrings) {

					Matcher matcher = pattern.matcher(ayString);
					if (!matcher.find())
						ayString = ayString + "}";

					Map<String, String> rowMap = JSONObject.parseObject(ayString,
							new TypeReference<Map<String, String>>() {
							});

					if (rowMap.get("type") != null) {

						Map<Integer, Object> map = new HashMap<>();

						if (rowMap.get("type").equals("column") && rowMap.get("value") != null
								&& !rowMap.get("value").equals("")) {

							intos.add(rowMap.get("column"));

							map.put(0, Integer.parseInt(rowMap.get("value")));

							values.add(map);

							if (rowMap.get("genre") != null && !rowMap.get("genre").equals("")) {
								map.put(3, rowMap.get("column"));
							}

						} else if (rowMap.get("type").equals("hash")) {

							intos.add(rowMap.get("column"));

							map.put(1, "hash");

							if (rowMap.get("foreignTable") != null) {
								map.put(2, rowMap.get("foreignTable"));
							}

							if (rowMap.get("foreignKey") != null) {
								map.put(3, rowMap.get("foreignKey"));
							} else {
								map.put(3, rowMap.get("column"));
							}

							values.add(map);

						}

						if (rowMap.get("genre") != null && !rowMap.get("genre").equals("")) {
							int genre = Integer.parseInt(rowMap.get("genre"));
							if (genre == 1) {
								map.put(4, genre);
							}
						}

					}

				}

				if (intos.size() != 0) {
					Map map = new HashMap<>();
					map.put("tableName", Key);
					map.put("intos", intos);
					map.put("values", values);
					allTable.add(map);
				}

			}

			System.out.println("1");
			
			Iterator<Map<String, Object>> iterator = allTable.iterator();

			Map intoMap = new HashMap<>();

			Map hash = new HashMap<>();

			while (iterator.hasNext()) {

				Map<String, Object> tableMap = iterator.next();

				String tableName = (String) tableMap.get("tableName");

				List<String> intos = (List) tableMap.get("intos");

				List values = (List) tableMap.get("values");

				Iterator<String> intoIterator = intos.iterator();

				String into = "";

				int i = 0;

				while (intoIterator.hasNext()) {

					if (into.equals(""))
						into = into + "(";

					String intovalue = intoIterator.next();

					into = into + intovalue + ",";

					Map<Integer, Object> valueMap = (Map<Integer, Object>) values.get(i);

					if (valueMap.get(1) != null && valueMap.get(2) != null) {
						String foreignTable = (String) valueMap.get(2);
						String foreignkey = intovalue;
						Map<String, Object> map = new HashMap<>();
						map.put("oneselfTable", tableName);
						map.put("foreignTable", valueMap.get(2));
						map.put("foreignkey", intovalue);
						Map<String, Object> thistable = (Map) hash.get(tableName);
						if (thistable == null)
							thistable = new HashMap<String, Object>();
						thistable.put(UUID.randomUUID().toString(), map);
						hash.put(tableName, thistable);
					}

					i++;
				}

				/*
				 * 添加excel没有的字段
				 */
				/*if(tableName.equals(otherTable)){
					System.out.println("key="+otherTable);
					into = into  + "area,";
				}
				*/
				
				into = into.substring(0, into.length() - 1) + ")";

				intoMap.put(tableName, into);

			}

			for (List<String> excel : excleLists) {

				iterator = allTable.iterator();

				Map<String, Object> outHash = new HashMap<>();

				Map<String, Object> inHash = new HashMap<>();

				Map<String, Object> notRepeatHash = new HashMap<>();

				while (iterator.hasNext()) {

					Map<String, Object> tableMap = iterator.next();

					String tableName = (String) tableMap.get("tableName");

					List values = (List) tableMap.get("values");

					Iterator<Map<Integer, Object>> valueIterator = values.iterator();

					String value = "";

					while (valueIterator.hasNext()) {

						if (value.equals(""))
							value = value + "(";

						Map<Integer, Object> valueMap = valueIterator.next();

						if (valueMap.get(0) != null) {
							String v = String.valueOf(valueMap.get(0));
							value = value + "'" + excel.get(Integer.parseInt(v)) + "',";

							if (valueMap.get(4) != null) {
								Map<String, Object> notRepeat = new HashMap<>();
								notRepeat.put((String) valueMap.get(3), excel.get(Integer.parseInt(v)));
								Map<String, Object> notRepeats = new HashMap<>();
								notRepeats.put(UUID.randomUUID().toString(), notRepeat);
								notRepeatHash.put(tableName, notRepeats);
							}

						} else if (valueMap.get(1) != null) {
							String v = UUID.randomUUID().toString();
							value = value + "'" + v + "',";
							Map<String, Object> thisouttable = (Map) outHash.get(tableName);
							if (thisouttable == null)
								thisouttable = new HashMap<String, Object>();
							String column = String.valueOf(valueMap.get(3));
							thisouttable.put(column, v);
							outHash.put(tableName, thisouttable);
							if (valueMap.get(2) != null) {
								Map<String, Object> keyMap = (Map<String, Object>) hash.get(tableName);
								for (Map.Entry<String, Object> entry : keyMap.entrySet()) {
									Map<String, Object> thisTable = (Map<String, Object>) entry.getValue();
									Map<String, Object> map = (Map) inHash.get(tableName);
									if (map == null)
										map = new HashMap<String, Object>();
									Map<String, Object> thisintable = new HashMap<>();
									String foreignTable = (String) thisTable.get("foreignTable");
									String foreignkey = (String) thisTable.get("foreignkey");
									String oneselfTable = (String) thisTable.get("oneselfTable");
									thisintable.put("foreignTable", foreignTable);
									thisintable.put("foreignkey", foreignkey);
									thisintable.put("oneselfTable", oneselfTable);
									String column2 = String.valueOf(valueMap.get(3));
									thisintable.put(column2, v);
									map.put(UUID.randomUUID().toString(), thisintable);
									inHash.put(tableName, map);
								}
							}

							if (valueMap.get(4) != null) {
								Map<String, Object> notRepeat = new HashMap<>();
								notRepeat.put((String) valueMap.get(3), v);
								Map<String, Object> notRepeats = new HashMap<>();
								notRepeats.put(UUID.randomUUID().toString(), notRepeat);
								notRepeatHash.put(tableName, notRepeats);
							}
						}
	
					}

					/*
					 * 添加excel没有的字段
					 */
					/*System.out.println("tableName======="+tableName);
					if(tableName.equals(otherTable)){
						System.out.println("key="+otherTable);
						value = value + "'200',";
					}
					*/
					String into = (String) intoMap.get(tableName);

					value = value.substring(0, value.length() - 1) + ")";

					String notWhere = "";

					Map<String, Object> thisNotRepeat = (Map<String, Object>) notRepeatHash.get(tableName);

					if (thisNotRepeat != null && !thisNotRepeat.isEmpty()) {
						for (Map.Entry<String, Object> notentry : thisNotRepeat.entrySet()) {

							Map<String, Object> notvalues = (Map<String, Object>) notentry.getValue();

							for (Map.Entry<String, Object> notvalue : notvalues.entrySet()) {

								if (notvalue.getValue() != null) {
									notWhere = notWhere + notvalue.getKey() + " = '" + notvalue.getValue() + "' or ";
								} else {
									notWhere = notWhere + notvalue.getKey() + " is null or ";
								}

							}

						}
					}

					//System.out.println("notWhere=" + notWhere);

					String selectSql = "";

					if (notWhere != null && !notWhere.equals(""))
						selectSql = " select count(*) as c from " + tableName + " where "
								+ notWhere.substring(0, notWhere.length() - 3);

					String sql = " INSERT INTO " + tableName + " " + into + " VALUES " + value;

					//System.out.println("sql====="+sql);
					
					try {

						int count = 0;

						PreparedStatement ps;
						
						if (notWhere != null && !notWhere.equals("")){
							//System.out.println("selectSql="+selectSql);
							ps = connection.prepareStatement(selectSql); 
							ResultSet rs= ps.executeQuery();
							if (rs.next()) {
								count=rs.getInt("c");
				            }else{
								count=0;
							}
						}
						
						if (count > 0) {
							System.out.println("count=" + count);
							continue;
						}

						ps=connection.prepareStatement(sql);

						ps.executeUpdate();
						
					} catch (org.springframework.dao.DataIntegrityViolationException e) {
						// TODO: handle exception
						try {
							connection.rollback();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						String message = e.getMessage();
						return message;
					} catch (org.springframework.jdbc.BadSqlGrammarException e) {
						// TODO: handle exception
						try {
							connection.rollback();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						String message = e.getMessage();
						return message;
					} catch (Exception e) {
						// TODO: handle exception
						try {
							connection.rollback();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						e.printStackTrace();
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						String message =e.getMessage();
						return "写入失败("+message+")";
					}

					for (Map.Entry<String, Object> inentry : inHash.entrySet()) {

						String currentHash = "";

						Map<String, Object> inentryValues = (Map<String, Object>) inentry.getValue();

						for (Map.Entry<String, Object> inentryValue : inentryValues.entrySet()) {

							Map<String, Object> inentr = (Map<String, Object>) inentryValue.getValue();

							String foreignTable = (String) inentr.get("foreignTable");

							for (Map.Entry<String, Object> outentry : outHash.entrySet()) {

								if (outentry.getKey().equals(foreignTable)) {

									Map<String, Object> outMap = (Map<String, Object>) outentry.getValue();

									for (Map.Entry<String, Object> out : outMap.entrySet()) {

										for (Map.Entry<String, Object> in : inentryValues.entrySet()) {

											Map<String, Object> iv = (Map<String, Object>) in.getValue();

											for (Map.Entry<String, Object> i : iv.entrySet()) {

												String thisHash = inentry.getKey() + i.getKey() + out.getValue();

												if (currentHash.equals(thisHash)) {
													continue;
												} else {
													currentHash = thisHash;
												}

												if (out.getKey().equals(i.getKey())) {

													String sql2 = " update " + inentry.getKey() + " set " + i.getKey()
															+ " = '" + out.getValue() + "' where " + i.getKey() + " = '"
															+ i.getValue() + "'";

													try {
														this.getJdbcTemplate().execute(sql2);
													} catch (Exception e) {
														// TODO: handle
														// exception
														e.printStackTrace();
														try {
															connection.rollback();
														} catch (SQLException e1) {
															// TODO Auto-generated catch block
															e1.printStackTrace();
														}
														return "写入失败("+e.getMessage()+")";
													}

												}
											}
										}
									}
								}
							}
						}
					}

				}

			}
		} finally {
			try {
				connection.commit();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				try {
					connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}
		return "写入成功";
	}

}
